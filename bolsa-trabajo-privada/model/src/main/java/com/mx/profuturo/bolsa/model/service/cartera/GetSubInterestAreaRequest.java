package com.mx.profuturo.bolsa.model.service.cartera;

import java.io.Serializable;
import java.util.ArrayList;

public class GetSubInterestAreaRequest implements Serializable {

        private ArrayList<Integer> ids;

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }
}
