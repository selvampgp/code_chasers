package com.kgisl.premium.service;


import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.premium.models.Constants;
import com.kgisl.premium.models.Gender;
import com.kgisl.premium.models.Habbits;
import com.kgisl.premium.models.HealthIssues;
import com.kgisl.premium.models.Person;

@Transactional
@Service
public class PremiumCalculationService {
	
	public BigDecimal calculatePremium(Person person) {// base premium
        double premium = 5000;
        premium = applyAgeBasedCalculations(person, premium);
        if(Objects.nonNull(person.getGender()))
        premium = applyGenderBasedCalculations(person, premium);
        if(Objects.nonNull(person.getHealthIssues()) && !person.getHealthIssues().isEmpty())
        premium = applyHealthConditionBasedCalculations(person, premium);
        if(Objects.nonNull(person.getHabbits()) && !person.getHabbits().isEmpty())
        premium = applyHabitsBasedCalculations(person, premium);
        BigDecimal premiumAmount = new BigDecimal(premium);
        premiumAmount = premiumAmount.setScale(0,BigDecimal.ROUND_UP);
        return premiumAmount;
        
       }

	public static double applyHabitsBasedCalculations(Person person, double premium) {
        double percentage = 0.00;
        for (Habbits habbit : person.getHabbits()) {
            if (Constants.badHabbits.contains(habbit)) {
                percentage += 0.03;
            }else if (Constants.goodHabbits.contains(habbit)) {
                percentage -= 0.03;
            }
        }
        if(percentage != 0 || percentage != 0.00 ) {
            premium *=1+percentage;
        }
        return premium;
    }
	public static double applyHealthConditionBasedCalculations(Person person, double premium) {
		// Increase 1% per health issue
		/*for (HealthIssues issue : person.getHealthIssues()) {
			premium *= 1.01;
		}
		return premium;*/
		double percentage = 0.00;
		for (HealthIssues issue : person.getHealthIssues()) {
			percentage += 0.01;
		}
		if(percentage > 0) {
			premium *=1+percentage;
		}
		return premium;
	}

	public static double applyGenderBasedCalculations(Person person, double premium) {
		// Add gender specific adjustments
		if (person.getGender() == Gender.MALE) {
			premium *= 1.02;
		}
		return premium;
	}

	public static double applyAgeBasedCalculations(Person person, double premium) {
		// Increase premium depending on age
		if (person.getAge() >= 18) {
			premium *= 1.1;
		}
		if (person.getAge() >= 25) {
			premium *= 1.1;
		}
		if (person.getAge() >= 30) {
			premium *= 1.1;
		}
		if (person.getAge() >= 35) {
			premium *= 1.1;
		}
		if (person.getAge() >= 40) {
			// Add 20% per 5 years above 40
			int age = person.getAge() - 40;
			while (age >= 5) {
				premium *= 1.2;
				age -= 5;
			}
		}
		return premium;
	}
}
