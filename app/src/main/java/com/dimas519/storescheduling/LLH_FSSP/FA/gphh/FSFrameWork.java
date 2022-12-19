/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimas519.storescheduling.LLH_FSSP.FA.gphh;

/**
 *
 * @author Heni
 */

import com.dimas519.storescheduling.LLH_FSSP.FA.gfa.*;
import com.dimas519.storescheduling.LLH_FSSP.Problem;

public class FSFrameWork {

    /**
     * @param args the command line arguments
     */

    
    double probCO;
    double probM;
    double probR;
    double lambda;
    int poolSize;
    int popSize;
    int maxRun;
    int maxGen;
    int depth;
    int iPCO;
    int iPM;
    int iPR;
    // firefly
    double beta0;
    double gamma;
    fa FA;

    
    public FSFrameWork() {
        iPCO = 75;
        iPM = 2;
        iPR = 100 - iPCO - iPM;
        probCO = iPCO / 100; //0.70;
        probM = iPM / 100; //0.01;
        probR = iPR / 100; //1.0 - probCO - probR;
        poolSize = 300;
        popSize = 100;
        maxRun = 30;//50;
        maxGen = 10; //10;
        depth = 2;        
        lambda = 0.5;
        
        beta0 = 0.7;
        gamma = 1;
    }
    


    public int getProbCO(){
        return this.iPCO;
    };
    
    public int getProbM() {
        return this.iPM;
    };
    
    public int getPoolSize(){
        return poolSize;
    }
    
    public int getPopSize() {
        return popSize;
    }
    
    public int getMaxRun(){
        return maxRun;
    }
    
    public int getMaxGen(){
        return maxGen;
    }
    
    public int getDepth() {
        return depth;
    }  
    

    public void setProbCO(String pco){
        this.iPCO = Integer.parseInt(pco); 
    };
    
    public void setProbM(String pm) {
        this.iPM = Integer.parseInt(pm);
    };
    
    public void setPoolSize(String ps){
        this.poolSize = Integer.parseInt(ps);
    }
    
    public void setPopSize(String ps){
        this.popSize = Integer.parseInt(ps);
    }
    
    public void setMaxRun(String mr){
        this.maxRun = Integer.parseInt(mr);
    }
    
    public void setMaxGen(String mg){
        this.maxGen = Integer.parseInt(mg);
    }
    
   
    public void setDepth(String d){
        this.depth = Integer.parseInt(d);
    }

    
        public void setBeta0(String mr){
            this.beta0 = Double.parseDouble(mr);
        }
        
        public void setGamma(String mr){
            this.gamma = Double.parseDouble(mr);
        }
        

        public String getFAStr(Problem pr){
            String s = "";
            Problem p = pr;
          
//            p.ReadAProblem(fn);
            System.out.println("FA");
            FA = new fa(p, this.popSize, this.beta0, this.gamma, this.maxRun);
//            FA = new fa(p, 10, 0.07, 1, 10);
            FA.iteration();
            return s = FA.getBestInd().getStrSchedule()+
                    "Makespan = "+FA.getBestInd().getMakespan();
        }

        public fa getFA(){
            return this.FA;
        }
        


    
}
      
