package service;

import entity.ReceiptDetail;


public abstract class Receipt<T> {
    private ReceiptDetail receiptDetail;

    public abstract T generate();

    public void setReceiptDetail(ReceiptDetail receiptDetail) {
        this.receiptDetail = receiptDetail;
    }

    public ReceiptDetail getReceiptDetail() {
        return receiptDetail;
    }
}
