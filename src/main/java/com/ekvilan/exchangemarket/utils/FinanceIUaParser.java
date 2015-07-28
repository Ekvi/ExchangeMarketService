package com.ekvilan.exchangemarket.utils;

import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.models.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class FinanceIUaParser {
    @Autowired
    private DateUtils dateUtils;

    private final String USER_AGENT = "OPR/26.0.1656.60 Chrome/33.0.1750.154";
    private final String BUY = "?type=1";

    private List<Advertisement> removeAds;
    private Link link;
    private String userAction;

    public List<Advertisement> parseAdvertisements(Link link, String action) {
        List<Advertisement> advertisements = new ArrayList<Advertisement>();
        this.link = link;
        this.userAction = action;

        try {
            Document doc = Jsoup.connect(link.getLink() + action).userAgent(USER_AGENT).timeout(30000).get();
            advertisements = extractData(extractTable(doc));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return advertisements;
    }

    private Element extractTable(Document doc) {
        Elements tables = doc.getElementsByAttributeValue("class", "local_table local_table-black_market");
        return tables.get(0);
    }

    private List<Advertisement> extractData(Element table) {
        List<Advertisement> advertisements = new ArrayList<Advertisement>();
        List<Advertisement> invalidAds = new ArrayList<Advertisement>();

        Elements trTags = table.getElementsByTag("tr");

        for (Element tr : trTags) {
            String[] split = tr.toString().split("\n");

            if (split[0].contains("class=\"\"")) {
                Elements phone = tr.getElementsByAttribute("onclick");
                advertisements.add(createAdvertisement(tr.getElementsByTag("td"), phone.get(0)));
            } else if (split[0].contains("class=\"invalid\"")) {
                Elements phone = tr.getElementsByAttribute("onclick");
                Advertisement advertisement = createAdvertisement(tr.getElementsByTag("td"), phone.get(0));
                if (checkDate(advertisement.getDate()) > 0) {
                    advertisement.setDate(dateUtils.getYesterdayDate(advertisement.getDate()));
                }
                invalidAds.add(advertisement);
            }
        }

        removeAds = new ArrayList<Advertisement>(invalidAds);

        return advertisements;
    }

    private Advertisement createAdvertisement(Elements elements, Element phoneTag) {
        String date = dateUtils.createDate(elements.get(AdvertisementIndex.DATE_INDEX.getValue()).text());
        double rate = Double.parseDouble(elements.get(AdvertisementIndex.RATE_INDEX.getValue()).text());
        String currency = getCurrency(elements.get(AdvertisementIndex.SUM_INDEX.getValue()).text());
        String phone = elements.get(AdvertisementIndex.PHONE_INDEX.getValue()).text();
        String area = elements.get(AdvertisementIndex.AREA_INDEX.getValue()).text();
        String comment = elements.get(AdvertisementIndex.COMMENT_INDEX.getValue()).text();
        String action = userAction.equals(BUY) ? "купить" : "продать";
        int sum;

        try {
            String summa = elements.get(AdvertisementIndex.SUM_INDEX.getValue()).text();
            sum = Integer.parseInt(summa.substring(0, summa.length() - 2));
        } catch (NumberFormatException e) {
            sum = 0;
        }

        String[] split = phoneTag.toString().split("'");
        phone = extractFirstPart(phone) + convertBase64(split[1]);

        return new Advertisement("finance.i.ua",
                link.getCityName(), action, currency, sum, rate, phone, area, comment, date);
    }

    private String getCurrency(String value) {
        String currency;
        if(value.charAt(value.length() - 1) == '$') {
            currency = "доллар";
        } else if(value.charAt(value.length() - 1) == '€') {
            currency = "евро";
        } else {
            currency = "рубль";
        }
        return currency;
    }

    private int checkDate(String date) {
        String[] splitDate = date.split(" ");
        String[] splitNow = new Date().toString().split(" ");

        return splitDate[splitDate.length - 1].compareTo(splitNow[3]);
    }

    String extractFirstPart(String value) {
        String[] split = value.replaceFirst(" ", "").split(" ");
        return split[0];
    }

    String convertBase64(String value) {
        return new String(DatatypeConverter.parseBase64Binary(value));
    }

    public List<Advertisement> getRemoveAds() {
        return removeAds;
    }
}
