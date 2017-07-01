package com.liusong.app.ui.ipc;

import android.content.ContentResolver;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityContentResolverBinding;
import com.liusong.app.vo.ContactsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ContentProvider
 * 注意：需要权限：READ_CONTACTS，
 * Created by liu song on 2017/5/16.
 */

public class ContentResolverActivity extends BaseActivity implements View.OnClickListener {
    private ActivityContentResolverBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_content_resolver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_phone:
                if(getContacts()!=null){
                    showToast("系统联系人："+getContacts().toString());
                }
                break;
        }
    }

    /**
     * 查询系统联系人
     */
    private List<ContactsVo> getContacts() {
        List<ContactsVo> contacts = new ArrayList<>();
        ContentResolver resolver = getContentResolver();

        //从uri表中查询projection，条件是：selection=selectionargs,按sortOrder排序；
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME+" ASC"); //ASC升序排序,DESC降序排序

        while (cursor.moveToNext()) {
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            ArrayList<ContactsVo.Phone> phones = new ArrayList<>();

            //同一个联系人可能有多个电话号码
            int phoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            if (phoneNumber > 0) {
                /*Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.Data.CONTACT_ID+"="+contactId,
                        null,
                        null);*/
                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.Data.CONTACT_ID+"=?",
                        new String[]{String.valueOf(contactId)},
                        null);
                while (phoneCursor.moveToNext()) {
                    String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int typeId = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                    phones.add(new ContactsVo.Phone(getPhoneTypeNameById(typeId), phone));
                }
                phoneCursor.close();
            }
            contacts.add(new ContactsVo(contactId, name, phones));
        }
        cursor.close();
        return contacts;
    }

    /**
     * 根据类型获取电话号码的类型名称
     *
     * @param typeId
     * @return
     */
    private String getPhoneTypeNameById(int typeId) {
        switch (typeId) {
            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                return "home";
            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                return "mobile";
            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                return "work";
            default:
                return "none";
        }
    }
}
