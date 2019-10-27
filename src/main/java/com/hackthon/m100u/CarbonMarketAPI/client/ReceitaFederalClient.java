package com.hackthon.m100u.CarbonMarketAPI.client;

import com.hackthon.m100u.CarbonMarketAPI.domain.Invoice;
import com.hackthon.m100u.CarbonMarketAPI.domain.InvoiceRecord;
import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.model.ItemRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.ItemEntity;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReceitaFederalClient {


    private ItemRepository itemRepository;

    public ReceitaFederalClient(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Invoice readPage(String url) {

        WebDriverManager.chromedriver().setup();

        ChromeDriver browser = new ChromeDriver();
        try {
            browser.get(url);

            while (browser.findElements(By.className("RCod")).size() == 0
                    || browser.findElements(By.className("Rqtd")).size() == 0
                    || browser.findElements(By.className("RCod")).size() != browser.findElements(By.className("Rqtd")).size()) {
                Thread.sleep(1L);
            }

            List<ItemEntity> items = itemRepository.findAll();

            List<InvoiceRecord> invoiceRecords = new ArrayList<>();

            List<String> externalIds = browser.findElements(By.className("RCod")).stream().map(item -> item.getText()
                    .replaceAll("\\(Código: ", "")
                    .replaceAll(" \\)", "")).collect(Collectors.toList());

            List<Integer> quantities = browser.findElements(By.className("Rqtd")).stream().map(item -> Integer.parseInt(item.getText()
                    .replaceAll("Qtde.:", ""))).collect(Collectors.toList());


            for (int i = 0; i < externalIds.size(); i++) {
                int finalI = i;

                invoiceRecords.add(
                        new InvoiceRecord(
                                items.stream().filter(item -> item.getExternalId().equals(externalIds.get(finalI)))
                                        .findFirst().get().toItem(), quantities.get(i))
                );

            }

            String info_text = browser.findElementById("infos").getText();
            int index_begin = info_text.indexOf("Emissão: ") + "Emissão: ".length();

            Invoice invoice = new Invoice(new SimpleDateFormat("dd/MM/yyyy").parse(info_text.substring(index_begin, index_begin + 10)),
                    new Market(browser.findElementById("u20").getText(), new Date())
                    , (int) calculateKmItemsCarbon(invoiceRecords)
                    , (int) calculateBathItemsWater(invoiceRecords)
                    , calculateItemsPoint(invoiceRecords)
                    , invoiceRecords
            );
            return invoice;
        }catch (InterruptedException | ParseException e){
            throw new RuntimeException("ERROR READING DATA FROM RECEITA FEDERAL SERVICE");
        }finally {
            browser.close();
        }
    }




    private double calculateKmItemsCarbon(List<InvoiceRecord> invoiceRecords) {
        return invoiceRecords.stream()
                .map(ir -> ir.getQuantity() * ir.getItem().getServings() * ir.getItem().getCarbonServing())
                .reduce((double) 0, Double::sum) / 392 * 1.6;
    }

    private double calculateBathItemsWater(List<InvoiceRecord> invoiceRecords) {
        return invoiceRecords.stream()
                .map(ir -> ir.getQuantity() * ir.getItem().getServings() * ir.getItem().getWaterServing())
                .reduce((double) 0, Double::sum) / 65;
    }

    private char calculateItemsPoint(List<InvoiceRecord> invoiceRecords) {
        return (char) (65 + (invoiceRecords.stream()
                .map(ir -> ir.getItem().getCarbonServing())
                .reduce((double) 0, Double::sum) / 2 / invoiceRecords.size()));
    }
}
