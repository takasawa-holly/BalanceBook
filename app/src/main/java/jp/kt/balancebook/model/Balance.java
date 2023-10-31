package jp.kt.balancebook.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "balance_table")
public class Balance {

    @PrimaryKey(autoGenerate = true)
    public int id;

    // 金額
    @NonNull
    @ColumnInfo(name = "amount")
    public int amount;

    // 作成日
    //    @NonNull
    @ColumnInfo(name = "create_date")
    public Long createDate;

    // 更新日
    //    @NonNull
    @ColumnInfo(name = "update_date")
    public Long updateDate;

    // 種類（食費 or 水道光熱費 or etc...）
    @ColumnInfo(name = "tag")
    public String tag;

    // メモ 表紙
    @ColumnInfo(name = "memo")
    public String memo;

    public Balance(int amount) {
        this.amount = amount;
    }

    public int getBalanceId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int _amount) {
        this.amount = _amount;
    }

    public Long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Long _createDate) {
        this.createDate = _createDate;
    }

    public Long getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Long _updateDate) {
        this.updateDate = _updateDate;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String _tag) {
        this.tag = _tag;
    }

    public String getLabel() {
        return this.memo;
    }

    public void setLabel(String _label) {
        this.memo = _label;
    }

}