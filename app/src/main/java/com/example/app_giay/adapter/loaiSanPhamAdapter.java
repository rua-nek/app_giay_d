package com.example.app_giay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.app_giay.R;
import com.example.app_giay.dao.loaiSanPhamDao;
import com.example.app_giay.model.loaiSanPham;
import com.example.app_giay.view.activities.Ba.loaiSanPham.updateLspActivity;

import java.util.ArrayList;

public class loaiSanPhamAdapter extends ArrayAdapter<loaiSanPham> {
    private Context context;
    private int resource;
    private ArrayList<loaiSanPham> data;


    public loaiSanPhamAdapter(Context context, int resource, ArrayList<loaiSanPham> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView txtTen = convertView.findViewById(R.id.txtlsp_ten);
        TextView txtMoTa = convertView.findViewById(R.id.txtlsp_mota);
        ImageButton btnDelete = convertView.findViewById(R.id.btnDeleteLsp);
        ImageButton btnUpdate = convertView.findViewById(R.id.btnUpdateLsp);

        loaiSanPham loaiSanPham = data.get(position);
        txtTen.setText(loaiSanPham.getLsp_ten());
        txtMoTa.setText(loaiSanPham.getLsp_mota());
        btnDelete.setOnClickListener(v -> {
            Toast.makeText(context, "Xóa " + loaiSanPham.getLsp_ten(), Toast.LENGTH_SHORT).show();
        });
        btnDelete.setOnClickListener(v -> {
            // Xác định sản phẩm cần xóa dựa vào vị trí position
            loaiSanPham loaiSanPhamToRemove = data.get(position);

            // Xóa sản phẩm khỏi cơ sở dữ liệu
            deleteLoaiSanPham(loaiSanPhamToRemove.getLsp_ma());

            // Xóa sản phẩm khỏi danh sách
            data.remove(loaiSanPhamToRemove);

            // Thông báo cho Adapter biết là dữ liệu đã thay đổi và cần cập nhật giao diện
            notifyDataSetChanged();

            // Hiển thị thông báo xóa sản phẩm
            Toast.makeText(context, "Đã xóa " + loaiSanPhamToRemove.getLsp_ten(), Toast.LENGTH_SHORT).show();
        });




        btnUpdate.setOnClickListener(v -> {
            Toast.makeText(context, "Cập nhật " + loaiSanPham.getLsp_ten(), Toast.LENGTH_SHORT).show();
        });
        return convertView;

    }

    private void deleteLoaiSanPham(int lspMa) {
        loaiSanPhamDao loaiSanPhamDao = new loaiSanPhamDao(context);
        loaiSanPhamDao.deleteLoaiSanPham(lspMa);
    }
}