package com.kgfsl.testservice;


public class AccountServiceTest {/*
	
	@Mock
	private AccountsDao accountsDao;

	@InjectMocks
	private AccountsServiceImpl accountsService;

	private ConsumerAccResource consumerAccResource = new ConsumerAccResource();

	private Integer consumerId;
	
	private Integer accountId;
	
	private Integer lookupId;

	public AccountServiceTest(){
		
		MockitoAnnotations.initMocks(this);
	}

	public void setConsumerId(Integer consumerId) {

		this.consumerId = consumerId;
	}

	public void buildAccountDetails() {
		
		consumerAccResource.setAcoConsumerId(consumerId);
		
		when(accountsDao.getAccountsDetails(consumerId)).thenReturn(consumerAccResource);		
	}

	public void checkConsumerAllAccountsById() {

		ConsumerAccResource returnData = accountsService.getAccountsDetails(consumerId);
		
		Assert.assertNotNull(returnData);
		
		Mockito.verify(accountsDao, Mockito.atLeast(1)).getAccountsDetails(consumerId);
	}

	public void buildPaymentDetails() {
		
		ConsumerTransResource consumerTransResource = new ConsumerTransResource();
		consumerTransResource.setAccount(accountId);
		
		when(accountsDao.getPaymentHistory(consumerId, "TRANS_HEADER")).thenReturn(Arrays.asList(consumerTransResource));	
	}

	public void checkConsumerPaymentHistory() {

		List<ConsumerTransResource> returnData = accountsService.getPaymentHistory(consumerId, "TRANS_HEADER");
		
		Assert.assertTrue(!returnData.isEmpty());
		
		Mockito.verify(accountsDao, Mockito.atLeast(1)).getPaymentHistory(consumerId, "TRANS_HEADER");
	}

	public void setAccountId(Integer accountId) {

		this.accountId = accountId;
	}

	public void setGroupedAccountDetails() {
		
		ConsumerTransResource consumerTransResource = new ConsumerTransResource();
		consumerTransResource.setAccount(accountId);
		
		when(accountsDao.getAccountSummary(accountId, "ACCOUNT_INFO", "ACCOUNT_INFO_BY_REF_ID")).thenReturn(Arrays.asList(consumerTransResource));	
	}

	public void checkGroupedAccountDetails() {

        List<ConsumerTransResource> returnData = accountsService.getAccountSummary(accountId, "ACCOUNT_INFO", "ACCOUNT_INFO_BY_REF_ID");
		
		Assert.assertTrue(!returnData.isEmpty());
		
		Mockito.verify(accountsDao, Mockito.atLeast(1)).getAccountSummary(accountId, "ACCOUNT_INFO", "ACCOUNT_INFO_BY_REF_ID");
		
	}

	public void setLookUpId(DataTable dataTable) {
		
		Map<String, Integer> map = dataTable.asMap(String.class, Integer.class);
		
		this.lookupId = map.get("lookupId");
	}

	public void buildLookuoData() {
		
		String keyValue = "test value";
		
		List<?> lookupList = Arrays.asList(keyValue);
		
		when(accountsDao.getGeneralLookupValue(lookupId)).thenReturn(Arrays.asList());	
	}

	public void checkLookUpValue() {
		
        List<?> returnData = accountsService.getGeneralLookupValue(lookupId);
		
		Assert.assertNotNull(returnData);
		
		Mockito.verify(accountsDao, Mockito.atLeast(1)).getGeneralLookupValue(lookupId);
	}

	public void buildAccountDetailsById() {
		
        consumerAccResource.setAcoAccountId(new BigInteger(accountId.toString()));
		
		when(accountsDao.getAccounts(accountId)).thenReturn(consumerAccResource);
	}

	public void checkAccountDetailsById() {

		ConsumerAccResource returnData = accountsService.getAccounts(accountId);
		
		Assert.assertEquals(new BigInteger(accountId.toString()), returnData.getAcoAccountId());
		
		Mockito.verify(accountsDao, Mockito.atLeast(1)).getAccounts(accountId);
	}

*/}
