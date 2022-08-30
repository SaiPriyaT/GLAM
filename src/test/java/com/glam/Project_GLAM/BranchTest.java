package com.glam.Project_GLAM;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.glam.beans.Branch;
import com.glam.glam.ProjectGlamApplication;
import com.glam.repository.BranchRepository;


@SpringBootTest(classes= ProjectGlamApplication.class)
public class BranchTest {

	@Test
	void contextLoads() {
}
	@Autowired
	private BranchRepository branchRepository;
	
	@Test
	@Order(2)
	@Rollback(value = false)
		public void saveBranchTest() {
		Branch branch= Branch.builder()
				.branchName("sai")
				.branchID(1)
				.branchAddress("hzb")
				.build();
		branchRepository.save(branch);
		Assertions.assertThat(branch.getBranchID()).isGreaterThan(0);
				
			//ram
		}
	@Test
	@Order(1)
	public void getBranchTest() {
		Branch branch=branchRepository.findById(1).get();
		Assertions.assertThat(branch.getBranchID()).isEqualTo(1);
	}
	@Test
	@Order(3)
	@Rollback(value=false)
	public void updateBranchTest() {
		Branch branch= branchRepository.findById(1).get();
		branch.setBranchName("Ravi salon");
	Branch updateBranch=branchRepository.save(branch);
	Assertions.assertThat(updateBranch.getBranchName()).isEqualTo("Ravi salon");
	
	}
	@Test
	@Order(4)
	public void getListOfBranchesTest() {
		List<Branch> branch=branchRepository.findAll();
		Assertions.assertThat(branch.size()).isGreaterThan(0);
	}
	
}