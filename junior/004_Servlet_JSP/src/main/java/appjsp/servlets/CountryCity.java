package appjsp.servlets;

import appjsp.entities.enums.Cities;
import appjsp.entities.enums.Countries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static appjsp.entities.enums.Cities.*;
import static appjsp.entities.enums.Countries.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 18.10.2018
 */
@WebServlet(urlPatterns = {"/countryCity"})
public class CountryCity extends HttpServlet {
    private final HashMap<Countries, HashMap<String, Cities>> countriesCities = new HashMap<>();
    private final HashMap<String, Countries> countries = new HashMap<>();
    private HashMap<String, Cities> cities;

    @Override
    public void init() throws ServletException {
        countries.put("Poland", POLAND);
        countries.put("Belarussia", BELARUSSIA);
        countries.put("Russia", RUSSIA);
        HashMap<String, Cities> poland = new HashMap<>();
        poland.put("Poznan", POSNAN);
        poland.put("Warshava", WARSHAVA);
        countriesCities.put(POLAND, poland);
        HashMap<String, Cities> russia = new HashMap<>();
        russia.put("Novgorod", NOVGOROD);
        russia.put("Smolensk", SMOLENSK);
        russia.put("St.-Peteresburg", PETERBURG);
        countriesCities.put(RUSSIA, russia);
        HashMap<String, Cities> belarussia = new HashMap<>();
        belarussia.put("Minsk", MINSK);
        belarussia.put("Brest", BREST);
        countriesCities.put(BELARUSSIA, belarussia);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        onCountryChange(Countries.valueOf(country));
//        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        writer.append("{");
        int i = 1;
        for (String s : cities.keySet()) {
            writer.append("\"" + s + "\":\"" + cities.get(s) + "\"");
            if (i++ == cities.size()) {
                break;
            } else {
                writer.append(",");
            }
        }
        writer.append("}");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        writer.append("{");
        int i = 1;
        for (String s : countries.keySet()) {
            writer.append("\"" + s + "\":\"" + countries.get(s) + "\"");
            if (i++ == countries.size()) {
                break;
            } else {
                writer.append(",");
            }
        }
        writer.append("}");
        writer.flush();
        writer.close();
    }

    public void onCountryChange(Countries country) {
        if (country != null && !country.equals("")) {
            cities = countriesCities.get(country);
        } else {
            cities = new HashMap<>();
        }
    }
}
