package com.kgisl.premium.models;

import java.util.HashSet;
import java.util.Set;

public interface Constants {
	Set<Habbits> goodHabbits = new HashSet<Habbits>() {
		{
			add(Habbits.DailyExercise);
		}
	};

	Set<Habbits> badHabbits = new HashSet<Habbits>() {
		{
			add(Habbits.Smoking);
			add(Habbits.Alchohol);
			add(Habbits.Drugs);
		}
	};
}
