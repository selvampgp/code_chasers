package com.kgfsl.behaviours;

import com.kgfsl.testservice.AccountServiceTest;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountServiceBehaviour {/*

	public AccountServiceTest accountServiceTest;

	@Before
	public void intializeService(){
		
		accountServiceTest = new AccountServiceTest();
	}

	@Given("^an existing consumer id (\\d+)$")
	public void an_existing_consumer_id(Integer consumerId) throws Throwable {

		accountServiceTest.setConsumerId(consumerId);
	}

	@When("^a cosumer need to get their account details$")
	public void a_cosumer_need_to_get_their_account_details() throws Throwable {

		accountServiceTest.buildAccountDetails();
	}

	@Then("^consumer should get their account details against consumer id$")
	public void consumer_should_get_their_account_details_against_consumer_id() throws Throwable {

		accountServiceTest.checkConsumerAllAccountsById();
	}

	@When("^a cosumer need to get their payment details$")
	public void a_cosumer_need_to_get_their_payment_details() throws Throwable {

		accountServiceTest.buildPaymentDetails();
	}

	@Then("^consumer should get their payment details against account id$")
	public void consumer_should_get_their_payment_details_against_account_id() throws Throwable {

		accountServiceTest.checkConsumerPaymentHistory();
	}

	@Given("^a account id (\\d+)$")
	public void a_account_id(Integer accountId) throws Throwable {

		accountServiceTest.setAccountId(accountId);
	}

	@When("^a cosumer need to get grouped accounts$")
	public void a_cosumer_need_to_get_grouped_accounts() throws Throwable {

		accountServiceTest.setGroupedAccountDetails();
	}

	@Then("^consumer should get their group account details against consumer id$")
	public void consumer_should_get_their_group_account_details_against_consumer_id() throws Throwable {

		accountServiceTest.checkGroupedAccountDetails();
	}

	@Given("^a look up id$")
	public void a_look_up_id(DataTable lookupKeyId) throws Throwable {
	    
		accountServiceTest.setLookUpId(lookupKeyId);
	}

	@When("^we need to get general lookup value against given lookup id$")
	public void we_need_to_get_general_lookup_value_against_given_lookup_id() throws Throwable {
	 
		accountServiceTest.buildLookuoData();
	}

	@Then("^the lookup value should be returned$")
	public void the_lookup_value_should_be_returned() throws Throwable {
	 
		accountServiceTest.checkLookUpValue();
	}
	
	@When("^a cosumer need to get a particular account details$")
	public void a_cosumer_need_to_get_a_particular_account_details() throws Throwable {

		accountServiceTest.buildAccountDetailsById();
	}

	@Then("^consumer should get thw  account details against account id$")
	public void consumer_should_get_thw_account_details_against_account_id() throws Throwable {

		accountServiceTest.checkAccountDetailsById();
	}
*/}
