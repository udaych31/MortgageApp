package com.hcl.mortgage.app.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ApplicationQueueListResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, List<QueueListDto>> queueList;
	
	public ApplicationQueueListResponse() {
		super();
	}

	public Map<String, List<QueueListDto>> getQueueList() {
		return queueList;
	}

	public void setQueueList(Map<String, List<QueueListDto>> queueList) {
		this.queueList = queueList;
	}
	
	

}
