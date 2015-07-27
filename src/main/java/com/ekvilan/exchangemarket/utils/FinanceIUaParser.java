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
    private List<Link> links = new ArrayList<Link>();
    private Link link;
    private String userAction;

    public FinanceIUaParser() {
        initLinks();
    }

    private void initLinks() {
        links.add(new Link("Андрушевка", "http://finance.i.ua/market/andrushevka/"));
        links.add(new Link("Белая Церковь", "http://finance.i.ua/market/belaya%20tserkov/"));
        links.add(new Link("Бровары", "http://finance.i.ua/market/brovari/"));
        links.add(new Link("Васильков", "http://finance.i.ua/market/vasilkov/"));
        links.add(new Link("Верхнеднепровск", "http://finance.i.ua/market/verhnedneprovsk/"));
        links.add(new Link("Винница", "http://finance.i.ua/market/vinnitsa/"));
        links.add(new Link("Вольногорск", "http://finance.i.ua/market/volnogorsk/"));
        links.add(new Link("Горловка", "http://finance.i.ua/market/gorlovka/"));
        links.add(new Link("Днепродзержинск", "http://finance.i.ua/market/dneprodzerzhinsk/"));
        links.add(new Link("Днепропетровск", "http://finance.i.ua/market/dnepropetrovsk/"));
        links.add(new Link("Донецк", "http://finance.i.ua/market/donetsk/"));
        links.add(new Link("Дружковка", "http://finance.i.ua/market/druzhkovka/"));
        links.add(new Link("Житомир", "http://finance.i.ua/market/zhitomir/"));
        links.add(new Link("Запорожье", "http://finance.i.ua/market/zaporozhe/"));
        links.add(new Link("Знаменка", "http://finance.i.ua/market/znamenka/"));
        links.add(new Link("Ивано-Франковск", "http://finance.i.ua/market/ivano-frankovsk/"));
        links.add(new Link("Изяслав", "http://finance.i.ua/market/izyaslav/"));
        links.add(new Link("Ирпень", "http://finance.i.ua/market/irpen/"));
        links.add(new Link("Калуш", "http://finance.i.ua/market/kalush/"));
        links.add(new Link("Киев", "http://finance.i.ua/market/kiev/"));
        links.add(new Link("Кировоград", "http://finance.i.ua/market/kirovograd/"));
        links.add(new Link("Коломыя", "http://finance.i.ua/market/kolomiya/"));
        links.add(new Link("Комсомольск", "http://finance.i.ua/market/komsomolsk/"));
        links.add(new Link("Конотоп", "http://finance.i.ua/market/konotop/"));
        links.add(new Link("Костополь", "http://finance.i.ua/market/kostopol/"));
        links.add(new Link("Красноармейск", "http://finance.i.ua/market/krasnoarmeysk/"));
        links.add(new Link("Кременчуг", "http://finance.i.ua/market/kremenchug/"));
        links.add(new Link("Кривой Рог", "http://finance.i.ua/market/krivoy%20rog/"));
        links.add(new Link("Луганск", "http://finance.i.ua/market/lugansk/"));
        links.add(new Link("Львов", "http://finance.i.ua/market/lvov/"));
        links.add(new Link("Марганец", "http://finance.i.ua/market/marganets/"));
        links.add(new Link("Мариуполь", "http://finance.i.ua/market/mariupol/"));
        links.add(new Link("Николаев", "http://finance.i.ua/market/nikolaev/"));
        links.add(new Link("Никополь", "http://finance.i.ua/market/nikopol/"));
        links.add(new Link("Обухов", "http://finance.i.ua/market/obuhov/"));
        links.add(new Link("Одесса", "http://finance.i.ua/market/odessa/"));
        links.add(new Link("Орджоникидзе", "http://finance.i.ua/market/ordzhonikidze/"));
        links.add(new Link("Павлоград", "http://finance.i.ua/market/pavlograd/"));
        links.add(new Link("Полонное", "http://finance.i.ua/market/polonnoe/"));
        links.add(new Link("Полтава", "http://finance.i.ua/market/poltava/"));
        links.add(new Link("Прилуки", "http://finance.i.ua/market/priluki/"));
        links.add(new Link("Ровно", "http://finance.i.ua/market/rovno/"));
        links.add(new Link("Славута", "http://finance.i.ua/market/slavuta/"));
        links.add(new Link("Сумы", "http://finance.i.ua/market/sumi/"));
        links.add(new Link("Томаковка", "http://finance.i.ua/market/tomakovka/"));
        links.add(new Link("Ужгород", "http://finance.i.ua/market/uzhgorod/"));
        links.add(new Link("Фастов", "http://finance.i.ua/market/fastov/"));
        links.add(new Link("Харцызск", "http://finance.i.ua/market/hartsizsk/"));
        links.add(new Link("Харьков", "http://finance.i.ua/market/harkov/"));
        links.add(new Link("Хмельницкий", "http://finance.i.ua/market/hmelnitskiy/"));
        links.add(new Link("Черкассы", "http://finance.i.ua/market/cherkassi/"));
        links.add(new Link("Чернигов", "http://finance.i.ua/market/chernigov/"));
        links.add(new Link("Черновцы", "http://finance.i.ua/market/chernovtsi/"));

    }

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

    public List<Link> getLinks() {
        return links;
    }
}
