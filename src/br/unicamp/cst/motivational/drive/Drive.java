/*******************************************************************************
 * Copyright (c) 2016  DCA-FEEC-UNICAMP
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors:
 *     E. M. Fr�es, R. R. Gudwin - initial API and implementation
 ******************************************************************************/

package br.unicamp.cst.motivational.drive;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.cst.motivational.entity.DriveLevel;
import br.unicamp.cst.motivational.entity.Priority;
import br.unicamp.cst.motivational.entity.MotivationalMessages;
import br.unicamp.cst.motivational.exception.MotivationalException;

import java.util.ArrayList;
import java.util.List;

public abstract class Drive extends Codelet {

    private double relevance;
    private DriveLevel level;
    private List<Drive> primaryDrives;
    private String name;
    private Priority priority;

    public Drive(String name, DriveLevel level, Priority priority, double relevance) throws CodeletActivationBoundsException {
        this.setLevel(level);
        this.setName(name);
        this.setPriority(priority);
        this.setActivation(0.0d);
        this.setRelevance(relevance);
    }

    @Override
    public synchronized void calculateActivation() {

        synchronized (this) {
            try {
                if (getPrimaryDrives() != null && getLevel().equals(DriveLevel.SECUNDARY)) {
                    if (getPrimaryDrives().size() > 0) {
                        this.setActivation(calculateSecundaryDriveActivation() * getRelevance());

                    } else {
                        this.setActivation(calculateSimpleActivation() * getRelevance());
                    }
                } else {
                    this.setActivation(calculateSimpleActivation() * getRelevance());
                }
            } catch (CodeletActivationBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract double calculateSimpleActivation();

    public abstract double calculateSecundaryDriveActivation();

    public synchronized double getRelevance() {
        return relevance;
    }

    public synchronized void setRelevance(double relevance) {
        try {
            if (relevance < 0 && relevance > 1) {
                throw new MotivationalException(MotivationalMessages.MSG_VAR_RELEVANCE_NULL);
            }

            this.relevance = relevance;

        } catch (MotivationalException me) {
            me.printStackTrace();
        }
    }

    public synchronized DriveLevel getLevel() {
        return level;
    }

    public synchronized void setLevel(DriveLevel level) {
        try {
            if (level == null) {
                throw new MotivationalException(MotivationalMessages.MSG_VAR_LEVEL_NULL);
            }

            this.level = level;

        } catch (MotivationalException me) {
            me.printStackTrace();
        }

    }

    public synchronized List<Drive> getPrimaryDrives() {
        return primaryDrives;
    }

    public synchronized void setPrimaryDrives(ArrayList<Drive> primaryDrives) {
        this.primaryDrives = primaryDrives;
    }

    @Override
    public synchronized String getName() {
        return name;
    }

    @Override
    public synchronized void setName(String name) {
        try {
            if (name == null) {
                throw new MotivationalException(MotivationalMessages.MSG_VAR_NAME_NULL);
            }

            this.name = name;

        } catch (MotivationalException me) {
            me.printStackTrace();
        }

    }

    public synchronized Priority getPriority() {
        return priority;
    }

    public synchronized void setPriority(Priority priority) {
        try {
            if (priority == null) {
                throw new MotivationalException(MotivationalMessages.MSG_VAR_PRIORITY_NULL);
            }

            this.priority = priority;
        } catch (MotivationalException me) {
            me.printStackTrace();
        }
    }

}
