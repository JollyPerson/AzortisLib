/*
 * An open source utilities library used for Azortis plugins.
 *     Copyright (C) 2019  Azortis
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.azortislib.inventory.object;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Condition<T> {
    private Set<Predicate<T>> requirements;
    private Action<T> action;

    public Condition() {
        requirements = new HashSet<>();

    }
    public Set<Predicate<T>> getRequirements() {
        return requirements;
    }

    public Condition<T> setRequirements(Set<Predicate<T>> requirements) {
        this.requirements = requirements;
        return this;
    }

    public Condition<T> addRequirements(Predicate<T> requirement) {
        requirements.add(requirement);
        return this;
    }

    public Action<T> getAction() {
        return action;
    }

    public Condition<T> setAction(Action<T> action) {
        this.action = action;
        return this;
    }

    public void test(T event) {
        for(Predicate<T> predicate : requirements) {
            if(!predicate.test(event)) return;
        }
        action.action(event);
    }
}
