package com.example.sharesdk;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import c352.pack.namespace.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * TODO<������>
 * @data: 2014-7-21 ����2:45:38
 * @version: V1.0
 */

public class SharePopupWindow extends PopupWindow {

    private Context context;
    private PlatformActionListener platformActionListener;
    private ShareParams shareParams;

    public SharePopupWindow(Context cx) {
        this.context = cx;
    }

    public PlatformActionListener getPlatformActionListener() {
        return platformActionListener;
    }

    public void setPlatformActionListener(
            PlatformActionListener platformActionListener) {
        this.platformActionListener = platformActionListener;
    }

    public void showShareWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.share_layout,null);
        GridView gridView = (GridView) view.findViewById(R.id.share_gridview);
        ShareAdapter adapter = new ShareAdapter(context);
        gridView.setAdapter(adapter);

        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        // ȡ����ť
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // ���ٵ�����
                dismiss();
            }
        });

        // ����SelectPicPopupWindow��View
        this.setContentView(view);
        // ����SelectPicPopupWindow��������Ŀ�
        this.setWidth(LayoutParams.FILL_PARENT);
        // ����SelectPicPopupWindow��������ĸ�
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // ����SelectPicPopupWindow��������ɵ��
        this.setFocusable(true);
        // ����SelectPicPopupWindow�������嶯��Ч��
        this.setAnimationStyle(R.style.AnimBottom);
        // ʵ����һ��ColorDrawable��ɫΪ��͸��
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // ����SelectPicPopupWindow��������ı���
        this.setBackgroundDrawable(dw);

        gridView.setOnItemClickListener(new ShareItemClickListener(this));

    }

    private class ShareItemClickListener implements OnItemClickListener {
        private PopupWindow pop;

        public ShareItemClickListener(PopupWindow pop) {
            this.pop = pop;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            share(position);
            pop.dismiss();

        }
    }

    /**
     * ����
     *
     * @param position
     */
    private void share(int position) {

        if (position == 1) {
            qq();
        } else if (position == 4) {
            qzone();
        } else if(position==5){
            shortMessage();
        }else{
            Platform plat = null;
            plat = ShareSDK.getPlatform(context, getPlatform(position));
            if (platformActionListener != null) {
                plat.setPlatformActionListener(platformActionListener);
            }

            plat.share(shareParams);
        }
    }

    /**
     * ��ʼ���������
     *
     * @param shareModel
     */
    public void initShareParams(ShareModel shareModel) {
        if (shareModel != null) {
            ShareParams sp = new ShareParams();
            sp.setShareType(Platform.SHARE_TEXT);
            sp.setShareType(Platform.SHARE_WEBPAGE);

            sp.setTitle(shareModel.getText());
            sp.setText(shareModel.getText());
            sp.setUrl(shareModel.getUrl());
            sp.setImageUrl(shareModel.getImageUrl());
            shareParams = sp;
        }
    }

    /**
     * ��ȡƽ̨
     *
     * @param position
     * @return
     */
    private String getPlatform(int position) {
        String platform = "";
        switch (position) {
        case 0:
            platform = "Wechat";
            break;
        case 1:
            platform = "QQ";
            break;
        case 2:
            platform = "SinaWeibo";
            break;
        case 3:
            platform = "WechatMoments";
            break;
        case 4:
            platform = "QZone";
            break;
        case 5:
            platform = "ShortMessage";
            break;
        }
        return platform;
    }

    /**
     * ����QQ�ռ�
     */
    private void qzone() {
        ShareParams sp = new ShareParams();
        sp.setTitle(shareParams.getTitle());
        sp.setTitleUrl(shareParams.getUrl()); // ����ĳ�����
        sp.setText(shareParams.getText());
        sp.setImageUrl(shareParams.getImageUrl());
        sp.setComment("�ҶԴ˷������ݵ�����");
        sp.setSite(shareParams.getTitle());
        sp.setSiteUrl(shareParams.getUrl());

        Platform qzone = ShareSDK.getPlatform(context, "QZone");

        qzone.setPlatformActionListener(platformActionListener); // ���÷����¼��ص� //
                                                                    // ִ��ͼ�ķ���
        qzone.share(sp);
    }

    private void qq() {
        ShareParams sp = new ShareParams();
        sp.setTitle(shareParams.getTitle());
        sp.setTitleUrl(shareParams.getUrl()); // ����ĳ�����
        sp.setText(shareParams.getText());
        sp.setImageUrl(shareParams.getImageUrl());
        sp.setComment("�ҶԴ˷������ݵ�����");
        sp.setSite(shareParams.getTitle());
        sp.setSiteUrl(shareParams.getUrl());
        Platform qq = ShareSDK.getPlatform(context, "QQ");
        qq.setPlatformActionListener(platformActionListener);
        qq.share(sp);
    }

    /**
     * ��������
     */
    private void shortMessage() {
        ShareParams sp = new ShareParams();
        sp.setAddress("");
        sp.setText(shareParams.getText()+"������ַ��"+shareParams.getUrl()+"���ܸ���Ŷ��");

        Platform circle = ShareSDK.getPlatform(context, "ShortMessage");
        circle.setPlatformActionListener(platformActionListener); // ���÷����¼��ص�
        // ִ��ͼ�ķ���
        circle.share(sp);
    }

}
