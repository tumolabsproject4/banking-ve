package edu.tumo.banking.domain.bank.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class BankModel {
    Long bankID;
    String bankName;
    String address;

    public BankModel() {
    }


    public BankModel(Long bankID, String bankName, String address) {
        this.bankID = bankID;
        this.bankName = bankName;
        this.address = address;
    }
    public BankModel( String bankName, String address) {

        this.bankName = bankName;
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankModel bankModel = (BankModel) o;
        return Objects.equals(bankID, bankModel.bankID) && Objects.equals(bankName, bankModel.bankName) && Objects.equals(address, bankModel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankID, bankName, address);
    }

    @Override
    public String toString() {
        return "BankModel{" +
                "bankID=" + bankID +
                ", bankName='" + bankName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

