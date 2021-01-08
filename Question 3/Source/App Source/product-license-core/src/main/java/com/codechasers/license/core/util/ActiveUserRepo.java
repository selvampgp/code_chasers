package com.codechasers.license.core.util;

import java.util.HashSet;
import java.util.Set;

/**Singleton class to maintain the login user list
 * 
 */
public class ActiveUserRepo {

	private final Set<Integer> activeUserLists=new HashSet<Integer>();
	
	private static Object threadSafe = new Object();
	
	private int constraintLimit = 0;

	private static volatile ActiveUserRepo activeUserRepo;
	
	private ActiveUserRepo(){
		
	}
	
	/**
	 * @return the allowedUserCount
	 */
	public  int getAllowedUserCount() {
		return constraintLimit;
	}

	
	/**
	 * @return the allowedUserCount
	 */
	public  void setAllowedUserCount(int constraintLimit) {
		 this.constraintLimit=constraintLimit;
	}

	
	/**
	 * @return the activeUserList
	 */
	public Set<Integer> getActiveUserList() {
		return activeUserLists;
	}

	public static ActiveUserRepo getInstance(){
		
		/** To avoid Double-checked locking issue**/
		ActiveUserRepo instance = activeUserRepo;
		
		if(instance==null){
			
			synchronized(threadSafe){
				
				instance=activeUserRepo;
				
				if(instance==null){
					activeUserRepo=instance=new ActiveUserRepo();
				}
			}
			
		}
		
		return instance;
	}
	
	/**
	 * @param activeUserRepo the activeUserList to set
	 */
	public synchronized void addOrRemoveUser(int activeUserId,boolean addFlag) {
		
		if(addFlag){
			activeUserLists.add(activeUserId);
		}
		else{
			activeUserLists.remove(activeUserId);
		}
		
		System.out.println(activeUserId);
	}
	
	
	public synchronized boolean isUserActive(Integer userId){
		
		return activeUserLists.contains(userId) || activeUserLists.size()<constraintLimit;
	}
	
	
	
}
