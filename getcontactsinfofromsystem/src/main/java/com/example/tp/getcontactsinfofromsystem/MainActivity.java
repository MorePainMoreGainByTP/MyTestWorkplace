package com.example.tp.getcontactsinfofromsystem;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView name, phone;
    private final int PICK_PHONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHONE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentData = data.getData();
                CursorLoader loader = new CursorLoader(this, contentData, null, null, null, null);
                String phoneMNumber = "此联系人暂未存入号码";
                Cursor cursor = loader.loadInBackground();
                if (cursor.moveToFirst()) {
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String mName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
                    if (phones.moveToFirst()) {
                        phoneMNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                    name.setText(mName);
                    phone.setText(phoneMNumber);
                }
                cursor.close();
            }
        }
    }

    public void jumpActivity(View v) {
        System.out.println("start");
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
        System.out.println("end");
    }

    public void viewBrowser(View v) {
        Intent intent = new Intent();
        String address = "http://www.baidu.com";
        Uri uri = Uri.parse(address);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialPhone(View v) {
        Intent intent = new Intent();
        String mPhone = "tel:15520452757";
        Uri uri = Uri.parse(mPhone);
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void viewContacts(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("vnd.android.cursor.item/phone");
        startActivityForResult(intent, PICK_PHONE);
    }

    public void editContacts(View v) {
        Intent intent = new Intent();
        String data = "content://com.android.contacts/contacts/1";
        Uri uri = Uri.parse(data);
        intent.setAction(Intent.ACTION_EDIT);
        intent.setData(uri);
        startActivity(intent);
    }
}
