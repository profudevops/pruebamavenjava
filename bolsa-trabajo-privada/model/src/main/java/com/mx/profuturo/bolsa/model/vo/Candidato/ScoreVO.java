package com.mx.profuturo.bolsa.model.vo.Candidato;

import java.util.LinkedList;

public class ScoreVO extends TagVO {

	LinkedList<TagVO> tags = new LinkedList<>();


	public LinkedList<TagVO> getTags() {
		return tags;
	}

	public void setTags(LinkedList<TagVO> tags) {
		this.tags = tags;
	}
}
