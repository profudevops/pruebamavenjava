package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.List;

public class Grade implements Serializable {

    private Score score;
    private List<Tag> tags;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
