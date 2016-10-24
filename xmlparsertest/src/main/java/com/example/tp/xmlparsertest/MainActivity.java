package com.example.tp.xmlparsertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
    }

    public void parserXml(View v) {
        StringBuilder strBuilder = new StringBuilder("");
        XmlPullParser xmlParser = getResources().getXml(R.xml.xml_for_test);
        System.out.println("1");
        try {
            while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                System.out.println("========");
                if (xmlParser.getEventType() == XmlPullParser.START_TAG) {
                    String tagName = xmlParser.getName();
                    if (tagName.equals("book")) {
                        System.out.println("2");
                        String price = xmlParser.getAttributeValue(null, "price");
                        String name = xmlParser.getAttributeValue(1);
                        System.out.println("3");
                        String text = xmlParser.nextText();
                        strBuilder.append("价格：" + price + "\t书名：" + name + "\t描述：" + text + "\n");
                        System.out.println("4");
                    } else System.out.println("标签名：" + tagName);
                }
                System.out.println("5");
                xmlParser.next();
                System.out.println("6");
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("7");
        text.setText(strBuilder.toString());
        System.out.println("8");
    }

}
