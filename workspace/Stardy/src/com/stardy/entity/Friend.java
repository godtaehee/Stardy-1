package com.stardy.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Friend {

	private int targetId;
	private int originId;
}
