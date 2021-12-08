package edu.tumo.banking.domain.bank.model;

import java.util.Arrays;
import java.util.Objects;

public class BankModel {
    Long bankID;
    String bankName;
    String address;
    Byte[] image;

    public BankModel() {
    }

    public BankModel(Long bankID, String bankName, String address, Byte[] image) {
        this.bankID = bankID;
        this.bankName = bankName;
        this.address = address;
        this.image = image;
    }

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankModel bankModel = (BankModel) o;
        return Objects.equals(bankID, bankModel.bankID) && Objects.equals(bankName, bankModel.bankName) && Objects.equals(address, bankModel.address) && Arrays.equals(image, bankModel.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bankID, bankName, address);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "BankModel{" +
                "bankID=" + bankID +
                ", bankName='" + bankName + '\'' +
                ", address='" + address + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}

