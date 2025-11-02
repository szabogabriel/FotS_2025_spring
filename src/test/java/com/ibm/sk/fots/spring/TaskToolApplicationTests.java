package com.ibm.sk.fots.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.ibm.sk.fots.spring.dto.TaskCreate;

@SpringBootTest
@AutoConfigureMockMvc
class TaskToolApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void taskCreate_fieldsAreSetCorrectly() {
		TaskCreate taskCreate = new TaskCreate();
		taskCreate.setTitle("Test title");
		taskCreate.setDescription("Test Description");
		taskCreate.setCompleted(false);
		taskCreate.setDueDate("2025-11-21T11:00:00");
		taskCreate.setPriority("MEDIUM");
		taskCreate.setTags(java.util.List.of("work", "urgent"));

		assertThat(taskCreate.getTitle()).isEqualTo("Test title");
		assertThat(taskCreate.getDescription()).isEqualTo("Test Description");
		assertThat(taskCreate.isCompleted()).isFalse();
		assertThat(taskCreate.getDueDate()).isEqualTo("2025-11-21T11:00:00");
		assertThat(taskCreate.getPriority()).isEqualTo("MEDIUM");
		assertThat(taskCreate.getTags()).containsExactly("work", "urgent");
	}

}
