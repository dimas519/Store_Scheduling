/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimas519.storescheduling.LLH_FSSP.FA.gfa;

import com.dimas519.storescheduling.LLH_FSSP.Problem;
import com.dimas519.storescheduling.LLH_FSSP.Schedule;
import com.dimas519.storescheduling.LLH_FSSP.Scheduler;

import com.dimas519.storescheduling.LLH_FSSP.Slot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author FTIS
 */

public class fa {
    
    Problem problem;
    int popSize;
    int maxGen;
    individual [] population;
    individual [] newPopulation;
    double beta0, gamma;
    double [][] mAttract;
    int [][] mDistance;
    individual bestInd;
    
    public fa (Problem p, int pS, double b0, double g, int mG) {
        problem = p;
        popSize = pS;
        beta0 = b0;
        gamma = g;
        maxGen = mG;
        population = new individual [pS];
    }
    
    void initialization(){
        for (int i=0; i<this.popSize; i++){
            List<Integer> li = new ArrayList<>(); 
            population[i] = new individual (this.problem.JOB_NUM);
//            population[i].ordering = new int [nJob];
        
            for (int j=0; j<population[i].size; j++){
                li.add(j);
            }
            java.util.Collections.shuffle(li);
            for (int j=0; j<population[i].size; j++){
                population[i].ordering[j] = li.get(j);
            }
            evaluation(i); // getfitness
        }
    }    
     
    boolean isTerminate(int iter){
        return iter > this.maxGen;
    }
    
    void evaluation(int i){
        // hitung fitness
        Scheduler S = new Scheduler(this.problem);
        Schedule s = new Schedule (0, this.problem.JOB_NUM);
        s.setOrdering(population[i].ordering);
        Slot [][] ss = S.getScheduling(this.problem, s);
        population[i].setMakeSpan(ss[this.problem.JOB_NUM-1][this.problem.MACHINE_NUM-1].getFinish());
        population[i].fitness = 1/(population[i].makespan + 1);
    }
    
    void evaluation(individual ind){
        // hitung fitness
        Scheduler S = new Scheduler(this.problem);
        Schedule s = new Schedule (0, this.problem.JOB_NUM);
        s.setOrdering(ind.ordering);
        Slot [][] ss = S.getScheduling(this.problem, s);
        ind.setMakeSpan(ss[this.problem.JOB_NUM-1][this.problem.MACHINE_NUM-1].getFinish());
        ind.fitness = 1/(ind.makespan + 1);
    }

    int position(int [] ord, int s, int v) {
        for (int i=s; i<problem.JOB_NUM; i++){
            if (ord[i]==v) return i;
        }
// kalau sampai sini berarti tidak ketemu
        for (int i=0; i<ord.length; i++){
            System.out.print(ord[i]+" ");
        }
        System.out.println(s + " " + v);
        return -1;
    }
    
    // jarak berdasarkan banyaknya swap yang diperlukan
    int distance (int f1, int f2) {
        int d = 0;
        int [] o1 = new int [problem.JOB_NUM]; 
        int [] o2 = new int [problem.JOB_NUM]; 
        System.arraycopy(population[f1].ordering, 0, o1, 0, problem.JOB_NUM);
        System.arraycopy(population[f2].ordering, 0, o2, 0, problem.JOB_NUM);
        
        for (int i=0; i<problem.JOB_NUM-1; i++) {

            if (o1[i] != o2[i]) {
                int idx = position(o2, i+1, o1[i]);
                if (idx == -1) {
                    System.out.println("min 1");
                    System.out.println(o2.toString());
                }
                int temp = o2[i];
                o2[i] = o2[idx];
                o2[idx] = temp;
                /*
                for (int j=0; j<o2.length;j++){
                    System.out.print(o2[j]+" ");
                }
                System.out.println();*/
                d++;
            }
        }
        return d;
    }

    void distanceCalculation(){
        mDistance = new int [this.popSize][this.popSize];
        for (int i=0; i<this.popSize; i++) {
            for (int j=0; j<this.popSize; j++) {
                if (i==j) mDistance[i][j] = 0;
                else mDistance[i][j] = distance(i,j);
            }
        }
    }
    
    void attractiveness (){
        mAttract = new double [this.popSize][this.popSize];
        for (int i=0; i<this.popSize; i++) {
            for (int j=0; j<this.popSize; j++) {
                if (i==j) mAttract[i][j] = 0;
                else {
                    mAttract[i][j] = beta0 / (1+gamma*mDistance[i][j]*mDistance[i][j]);
                }
            }
        }
    }
    
    int theBest (int f){
        int b = 0;
        for (int i=1; i<this.popSize; i++) {
            if (mAttract[f][i] > mAttract[f][b]) b = i;
        }
        return b;
    }
    
    void movement(){
        newPopulation = new individual [this.popSize];
        
        for (int i=0; i<this.popSize;i++) {
            individual ind = new individual(problem.JOB_NUM);
            int b = theBest(i);
            ind.setOrdering(population[i].ordering);
            Random r = new Random();
            int m1 = r.nextInt(problem.JOB_NUM);
            int m2 = position(ind.ordering, 0, population[b].ordering[m1]);
            int v = population[i].ordering[m1];
            ind.ordering[m1] = population[b].ordering[m1];
            ind.ordering[m2] = v;
            evaluation(ind);
            newPopulation[i] = ind;
        }
        
        for (int i=0; i<this.popSize; i++) {
            population[i] = newPopulation[i];
        }
    }
    
    void setTheBestInd(){
        for (int i=1; i<popSize; i++){
            if (population[i].makespan < bestInd.makespan) {
                bestInd = population[i];
            }
        }
    }

    public void iteration(){
        int iter = 0;
        initialization();
        bestInd = population[0];
        setTheBestInd();
        while (!isTerminate(iter)){
            this.distanceCalculation();
            this.attractiveness();
            this.movement();
/*            System.out.println("-------------------------------");
            for (int k=0; k<this.popSize; k++){
                population[k].printSchedule();
            }*/
            setTheBestInd();
//            bestInd.printSchedule();
            iter++;
        }
        System.out.println("Best result :");
        bestInd.printSchedule();
    }
 
    void init(individual [] pop){
        population = new individual [this.popSize];
        for (int i=0; i<this.popSize; i++){
            population [i] = new individual(this.problem.JOB_NUM);
            population[i].setOrdering(pop[i].ordering);
            population[i].setMakeSpan(pop[i].makespan);
            population[i].setFitness(pop[i].fitness);
        }
    }
    
    public void iteration1(individual [] pop, individual best){
        int iter = 0;
        init(pop);
//        population = pop;
        bestInd = best;
        setTheBestInd();
        while (!isTerminate(iter)){
            this.distanceCalculation();
            this.attractiveness();
            this.movement();
/*            System.out.println("-------------------------------");
            for (int k=0; k<this.popSize; k++){
                population[k].printSchedule();
            }
*/            setTheBestInd();
//            bestInd.printSchedule();
            iter++;
        }
        System.out.println("Best result :");
        bestInd.printSchedule();
    }
    
    public individual [] getPopulation(){
        return this.population;
    }
    
    public individual getBestInd(){
        return this.bestInd;
    }
    
    public Slot [][] getSlots(){
        Scheduler S = new Scheduler(this.problem);
        Schedule s = new Schedule (0, this.problem.JOB_NUM);
        s.setOrdering(this.bestInd.ordering);
        Slot [][] ss = S.getScheduling(this.problem, s);
        return ss;
    }
}
