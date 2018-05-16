package com.criptonnetwork.type;

import java.util.List;

import com.google.common.collect.Lists;

public class GroupManager {

	public static List<Group> list = Lists.newArrayList();

	
	public static Group createGroup(Group g) {
		list.add(g);
		return g;
	}
	
	public static Group getGroup(String name) {
		for (Group g : list) {
			if (g.getName().equalsIgnoreCase(name))
				return g;
		}

		return null;
	}

}
