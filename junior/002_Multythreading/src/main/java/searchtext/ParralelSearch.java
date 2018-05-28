package searchtext;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.05.2018
 */

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class ParralelSearch {
    private volatile boolean finish = false;
    private final String root;
    private final String text;
    private final List<String> exts;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();


    public ParralelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    class MyFileVisitor extends SimpleFileVisitor<Path> {
        private PathMatcher matcher;

        public MyFileVisitor(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher(pattern);
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
            Path name = path.getFileName();
            if (matcher.matches(name)) {
                synchronized (files) {
                    files.add(name.toAbsolutePath().toString());
                    files.notify();
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }


    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                Path pathSource = Paths.get(root);
                for (String s : exts) {
                    String pattern = "glob:*.".concat(s);
                    try {
                        Files.walkFileTree(pathSource, new MyFileVisitor(pattern));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                finish = true;
                synchronized (files) {
                    files.notify();
                }
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                String fileName;
                String a;
                while (!finish) {
                    StringBuilder builder = new StringBuilder();
                    synchronized (files) {
                        if ((fileName = files.poll()) == null) {
                            try {
                                files.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                    }
                    System.out.println(fileName);
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        while ((a = reader.readLine()) != null) {
                            builder.append(a);
                        }
                    } catch (IOException y) {
                        y.printStackTrace();
                    }

                    if (builder.toString().contains(text)) {
                        paths.add(fileName);
                    }
                }
            }
        };
        search.start();
        read.start();
    }

    public synchronized List<String> result() {
        return paths;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("txt");
        ParralelSearch parralelSearch = new ParralelSearch("C:\\Users\\Ivan\\YandexDisk", "walk", list);
        parralelSearch.init();
        Thread.sleep(500);
        System.out.println(parralelSearch.result());
    }
}