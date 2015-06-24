package com.ekvilan.exchangemarket.utils;


import com.ekvilan.exchangemarket.models.Rates;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RatesExtractor {
    private final String USER_AGENT = "OPR/26.0.1656.60 Chrome/33.0.1750.154";
    private final String URL = "http://minfin.com.ua/currency/";
    private final String REPLACE= "<td>";
    private final String bankRegExp = ".+В банках.*\\n(.*\\d).*\\n(.*\\d)";
    private final String marketRegExp = ".*Рыночный.*\\n(.*\\d).*\\n(.*\\d)";
    private final String nbuRegExp = ".+НБУ.+\\n.+\\n(.+\\d)";

    private final String[] currencies = {"USD", "EUR", "RUB"};

    public List<Rates> getRates() {
        Document doc = null;
        String content = "";
        try {
            doc = Jsoup.connect(URL).userAgent(USER_AGENT).timeout(30000).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(doc != null) {
            content = doc.body().html();
        }

        return createList(extractRates(content, bankRegExp, false),
                extractRates(content, marketRegExp, false), extractRates(content, nbuRegExp, true));
    }

    private Rates extractRates(String content, String regexp, boolean isNbu) {
        Rate usd = extract(content, currencies[0] + regexp, isNbu);
        Rate eur = extract(content, currencies[1] + regexp, isNbu);
        Rate rub = extract(content, currencies[2] + regexp, isNbu);

        return new Rates(usd.buy, usd.sale, eur.buy, eur.sale, rub.buy, rub.sale);
    }

    private Rate extract(String content, String regexp, boolean isNbu) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        if(matcher.find()) {
            if(isNbu) {
                return new Rate(matcher.group(1).replace(REPLACE, "").trim(), matcher.group(1).replace(REPLACE, "").trim());
            } else {
                return new Rate(matcher.group(1).replace(REPLACE, "").trim(), matcher.group(2).replace(REPLACE, "").trim());
            }
        }
        return null;
    }

    private static class Rate {
        private String buy;
        private String sale;

        private Rate(String buy, String sale) {
            this.buy = buy;
            this.sale = sale;
        }
    }

    private List<Rates> createList(Rates banks, Rates market, Rates nbu) {
        List<Rates> ratesList = new ArrayList<Rates>();
        ratesList.add(banks);
        ratesList.add(market);
        ratesList.add(nbu);

        return ratesList;
    }
}
