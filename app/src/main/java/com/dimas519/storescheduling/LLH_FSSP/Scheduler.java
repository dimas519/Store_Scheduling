package com.dimas519.storescheduling.LLH_FSSP;/*
 * Kelas Scheduler
 * .

/**
 *
 * @author Heni
 */

public class Scheduler {

    int [] ordering;
    Problem P;
    public Slot [][] Scheduling;

    public Scheduler (Problem p){
        this.P = p;
    }

    private Double abs(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void printGanttChart(Slot [][] sch, Schedule sch1){
        for (int i=0;i<P.JOB_NUM;i++){
            System.out.println("Job ke-"+ sch1.ordering[i]);
            for (int j=0; j<P.MACHINE_NUM; j++) {
                System.out.println("mesin ke-"+j+" ["+sch[i][j].start+","+sch[i][j].finish+"]");
            }
        }
    }

    public void printScheduling(Slot [][] slot, Schedule sch){
        printGanttChart(slot, sch);
        System.out.println("Makespan = "+sch.getMakeSpan());
    }

    Double max(Double d1, Double d2){
        if (d1>d2) return d1; else return d2;
    }


    // -------- Johnson's -----------------------------------------
    void SplitJobs(int par1, int par2, int [] p1, int [] p2, Double [] ptj1, Double [] ptj2){

        // first partition
        for (int j=0;j<P.JOB_NUM;j++) {
            ptj1[j] = 0.0;
            for (int i=0; i<=par1; i++){
                ptj1[j] = ptj1[j] + P.Jobs[j].processing_time[i];
            }
        }
        // second partition
        for (int j=0;j<P.JOB_NUM;j++) {
            ptj2[j] = 0.0;
            for (int i=par2+1; i<P.MACHINE_NUM; i++){
                ptj2[j] = ptj2[j] + P.Jobs[j].processing_time[i];
            }
        }
        //
        int idx1 = 0;
        int idx2 = 0;
        for (int i=0;i<P.JOB_NUM;i++){
            if (ptj1[i] < ptj2[i]) {
                p1[idx1] = i;
                idx1++;
            }
            else {
                p2[idx2] = i;
                idx2++;
            }
        }
        // record the number of the jobs in each partition
        p1[P.JOB_NUM] = idx1;
        p2[P.JOB_NUM] = idx2;
    }

    int [] SortPartition(int [] p, int size, Double [] ptj){
        int [] sort = new int [size];

        System.arraycopy(p, 0, sort, 0, size);

        // sort ascending, selection sort
        for (int i=0; i<size-1; i++){
            int min = i;
            for (int j=i+1;j<size; j++) {
                if (ptj[sort[j]] <= ptj[sort[min]]) {
                    min = j;
                }
            }
            if (min != i){
                int t = sort[min];
                sort [min] = sort[i];
                sort [i] = t;
            }
        }
        return sort;
    }

    Schedule Johnson(){
        Schedule sch = new Schedule (-1, P.JOB_NUM);
        int [] p1 = new int [P.JOB_NUM+1];
        int [] p2 = new int [P.JOB_NUM+1];
        Double [] ptj1 = new Double [P.JOB_NUM];
        Double [] ptj2 = new Double [P.JOB_NUM];
        SplitJobs(0, 0, p1, p2, ptj1, ptj2);

        int [] s1;
        s1 = SortPartition(p1, p1[P.JOB_NUM], ptj1);
        int [] s2;
        s2 = SortPartition(p2, p2[P.JOB_NUM], ptj2);

        System.arraycopy(s1, 0, sch.ordering, 0, p1[P.JOB_NUM]);

        int j = p1[P.JOB_NUM];
        for (int i=p2[P.JOB_NUM]; i > 0; i--) {
            sch.ordering[j] = s2[i-1];
            j++;
        }
        Scheduling = getScheduling(P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // -------- Johnson's -----------------------------------------

    // -------- FCFS -----------------------------------------

    public Slot [][] getScheduling(Problem Pr, Schedule sch){
        Double [] t = new Double [Pr.JOB_NUM];
        Slot [][] scheduling;
        scheduling = new Slot [Pr.JOB_NUM][Pr.MACHINE_NUM];

        for (int i=0; i<Pr.JOB_NUM;i++) {
            t[i] = 0.0;
        }
        // untuk job pertama
        for (int i=0; i<Pr.MACHINE_NUM;i++) {
            scheduling[0][i] = new Slot(t[0],t[0]+Pr.Jobs[sch.ordering[0]].processing_time[i]);
            t[0] = t[0]+Pr.Jobs[sch.ordering[0]].processing_time[i];
        }

        // untuk job kedua
        for (int i=1; i<Pr.JOB_NUM; i++) {
            t[i] = max(scheduling[i-1][0].finish, t[i]);

            for (int j=0; j<Pr.MACHINE_NUM;j++) {
                scheduling[i][j] = new Slot(t[i],t[i]+Pr.Jobs[sch.ordering[i]].processing_time[j]);
                // look ahead, bahaya di j terakhir
                if (j+1< Pr.MACHINE_NUM)
                    t[i] = max(scheduling[i][j].finish, scheduling[i-1][j+1].finish);
            }
        }

        return scheduling;
    }

    public Slot [][] getSubScheduling(Problem Pr, Schedule sch, int numJ){
        Double [] t = new Double [Pr.JOB_NUM];
        Slot [][] scheduling;
        scheduling = new Slot [numJ][Pr.MACHINE_NUM];

        for (int i=0; i<numJ;i++) {
            t[i] = 0.0;
        }
        // untuk job pertama
        for (int i=0; i<Pr.MACHINE_NUM;i++) {
            scheduling[0][i] = new Slot(t[0],t[0]+Pr.Jobs[sch.ordering[0]].processing_time[i]);
            t[0] = t[0]+Pr.Jobs[sch.ordering[0]].processing_time[i];
        }

        // untuk job kedua
        for (int i=1; i<numJ; i++) {
            t[i] = max(scheduling[i-1][0].finish, t[i]);

            for (int j=0; j<Pr.MACHINE_NUM;j++) {
                scheduling[i][j] = new Slot(t[i],t[i]+Pr.Jobs[sch.ordering[i]].processing_time[j]);
                // look ahead, bahaya di j terakhir
                if (j+1< Pr.MACHINE_NUM)
                    t[i] = max(scheduling[i][j].finish, scheduling[i-1][j+1].finish);
            }
        }

        return scheduling;
    }

    public Schedule FCFS () {
        Double [] t = new Double [P.JOB_NUM];
        Schedule sch = new Schedule(0, P.JOB_NUM);
        sch.setDefaultOrdering();
        Scheduling = new Slot [P.JOB_NUM][P.MACHINE_NUM];

        for (int i=0; i<P.JOB_NUM;i++) {
            t[i] = 0.0;
        }
        // untuk job pertama
        for (int i=0; i<P.MACHINE_NUM;i++) {
            Scheduling[0][i] = new Slot(t[0],t[0]+P.Jobs[0].processing_time[i]);
            t[0] = t[0]+P.Jobs[0].processing_time[i];
        }

        // untuk job kedua
        for (int i=1; i<P.JOB_NUM; i++) {
            t[i] = max(Scheduling[i-1][0].finish, t[i]);

            for (int j=0; j<P.MACHINE_NUM;j++) {
                Scheduling[i][j] = new Slot(t[i],t[i]+P.Jobs[i].processing_time[j]);
                // look ahead, bahaya di j terakhir
                if (j+1< P.MACHINE_NUM)
                    t[i] = max(Scheduling[i][j].finish, Scheduling[i-1][j+1].finish);
            }
        }
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // -------- FCFS -----------------------------------------

    // -------- MOD -----------------------------------------
    int findBestPartition(Double [] pt){
        int part = 0;
        double minDiff = pt[P.MACHINE_NUM];
        double partPT1 = 0.0;
        double partPT2;

        // harus dibuat partisi jadi tidak boleh partisinya =
        for (int i=0; i<P.MACHINE_NUM-1; i++){
            partPT1 = partPT1 + pt[i];
            partPT2 = pt[P.MACHINE_NUM] - partPT1;
            if (Math.abs(partPT1 - partPT2) < minDiff) {
                minDiff = Math.abs(partPT1 - partPT2);
                part = i;
            }
        }
        return part;
    }

    public Schedule MOD (){
        Schedule sch = new Schedule(-1,P.JOB_NUM);
        Double [] pt = new Double [P.MACHINE_NUM+1];
        Double [] ptj1 = new Double [P.JOB_NUM+1];
        Double [] ptj2 = new Double [P.JOB_NUM+1];
        Double tpt = 0.0;

        int par;
        for (int i=0; i<P.MACHINE_NUM;i++) {
            pt [i] = 0.0;
            for (int j=0;j<P.JOB_NUM;j++){
                pt[i]=pt[i]+ P.Jobs[j].processing_time[i];
            }
            tpt = tpt + pt [i];
        }
        pt[P.MACHINE_NUM] = tpt;

        par = findBestPartition(pt);

        for (int j=0;j<P.JOB_NUM;j++) {
            ptj1[j] = 0.0;
            int mn = 0;
            for (int i=par; i>=0; i--){
                ptj1[j] = ptj1[j] + (i+1) * P.Jobs[j].processing_time[mn];
                mn++;
            }
        }

        for (int j=0;j<P.JOB_NUM;j++) {
            ptj2[j] = 0.0;
            int mn = P.MACHINE_NUM-1-par;
            for (int i=P.MACHINE_NUM-1; i>par; i--){
                ptj2[j] = ptj2[j] + (mn) * P.Jobs[j].processing_time[i];
                mn--;
            }
        }

        int [] m1 = new int [P.JOB_NUM];
        int [] m2 = new int [P.JOB_NUM];

        int idx1 = 0;
        int idx2 = 0;
        for (int i=0; i<P.JOB_NUM;i++){
            if (ptj1[i] < ptj2[i]) {
                m1[idx1] = i;
                idx1++;
            }
            else {
                m2[idx2] = i;
                idx2++;
            }
        }

        int [] s1 = SortPartition(m1, idx1, ptj1);
        int [] s2 = SortPartition(m2, idx2, ptj2);

        System.arraycopy(s1, 0, sch.ordering, 0, idx1);

        int j = idx1;
        for (int i=idx2; i > 0; i--) {
            sch.ordering[j] = s2[i-1];
            j++;
        }
        Scheduling = getScheduling(P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // -------- MOD -----------------------------------------

    // -------- NEH -----------------------------------------

    // insert value p at the position pos of array p
    void InsertJob(int [] p, int size, int v, int pos){
        if (pos < size-1) {
            int i = 0;
            while (i < pos) i++;
            for (int j=size-1; j>pos;j--) {
                p[j] = p[j-1];
            }
        }
        p[pos] = v;
    }

    Schedule NEHIteration (int [] ord){
        // starting with one job
        int [] minOrd = new int [P.JOB_NUM];
        int last = P.JOB_NUM-1;
        Double minMS = 0.0;
        Schedule minSch = new Schedule(-1, P.JOB_NUM);
        minSch.ordering[0] = ord[last];

        int [] parseq = new int [P.JOB_NUM];
//        parseq[0] = ord[last]; // the biggest tpj

        for (int i=1; i<P.JOB_NUM; i++){
            System.arraycopy(minSch.ordering, 0, parseq, 0, i);
            int idxmin = -1;
            last--;
            // untuk setiap kemungkinan posisi penyisipan
            for (int j=0;j<i+1;j++) {
                Schedule sc = new Schedule(-1, i+1);
                System.arraycopy(parseq, 0, sc.ordering, 0, i);
                InsertJob(sc.ordering, i+1, ord[last], j);

                // define a temporary Problem
                Problem Pr = new Problem();
                Pr.JOB_NUM = i+1;
                Pr.MACHINE_NUM = P.MACHINE_NUM;
                for (int k=0; k<Pr.JOB_NUM;k++) {
                    Pr.Jobs[k] = new Job(k);
                    Pr.Jobs[k].machine_num = Pr.MACHINE_NUM;
                }
                Schedule tsc = new Schedule(-1, i+1);
                int map [] = new int [i+1];
                for (int k=0;k<i+1;k++) {
                    map[k] = sc.ordering[k];
                    tsc.ordering[k] = k;
                }
                // salin processing time sesuai job yang muncul
                for (int k=0;k<i+1;k++) {
                    System.arraycopy(P.Jobs[map[k]].processing_time, 0, Pr.Jobs[k].processing_time, 0, Pr.MACHINE_NUM);
                }

                this.Scheduling = this.getScheduling(Pr, tsc);  // harus mendefinisikan problem baru
                if ((idxmin == -1) || (Scheduling[Pr.JOB_NUM-1][Pr.MACHINE_NUM-1].finish < minMS)) {
                    idxmin = j;
                    minSch = sc;
                    minMS = Scheduling[Pr.JOB_NUM-1][Pr.MACHINE_NUM-1].finish;
                }
            }
            // salin ke parseq
        }
        return minSch;
    }

    public Schedule NEH (){

        Double [] tpj = new Double [P.JOB_NUM];

        // processing time each job
        for (int i=0; i<P.JOB_NUM; i++) {
            tpj[i] = 0.0;
            for (int j=0; j<P.MACHINE_NUM; j++) {
                tpj[i] = tpj[i] + P.Jobs[i].processing_time[j];
            }
        }

        // original order
        int ord [] = new int [P.JOB_NUM];
        for (int i=0;i<P.JOB_NUM;i++) {
            ord[i] = i;
        }

        int [] sord = this.SortPartition(ord, P.JOB_NUM, tpj); // terurut menaik

        Schedule sch = this.NEHIteration(sord);

        Scheduling = getScheduling(P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // -------- NEH -----------------------------------------

    // -------- CDS -----------------------------------------

    public Schedule CDS(){
        // define surrogate, build its corresponding schedule, calculate its makespan,
        // take the schedule with the minimum makespan
//        int [] minOrd = new int [P.JOB_NUM];
//        int last = P.JOB_NUM-1;
        int idxmin = -1;
        Double minMS = 0.0;
        Schedule minSch = new Schedule(-1, P.JOB_NUM);
        //minSch.ordering[0] = ord[last];

        Double [] M1 = new Double [P.JOB_NUM];
        Double [] M2 = new Double [P.JOB_NUM];
        int [] p1 = new int [P.JOB_NUM+1];
        int [] p2 = new int [P.JOB_NUM+1];
        Schedule sch = new Schedule(-1, P.JOB_NUM);

        for (int k = 0; k<P.MACHINE_NUM-1; k++) {
            SplitJobs(k, P.MACHINE_NUM-1-k-1, p1, p2, M1, M2);

            // Johnson
            int [] s1;
            s1 = SortPartition(p1, p1[P.JOB_NUM], M1);
            int [] s2;
            s2 = SortPartition(p2, p2[P.JOB_NUM], M2);

            System.arraycopy(s1, 0, sch.ordering, 0, p1[P.JOB_NUM]);

            int j = p1[P.JOB_NUM];
            for (int i=p2[P.JOB_NUM]; i > 0; i--) {
                sch.ordering[j] = s2[i-1];
                j++;
            }
            this.Scheduling = this.getScheduling(P, sch);  // harus mendefinisikan problem baru
            sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
            if ((idxmin == -1) || (sch.getMakeSpan() < minMS)) {
                idxmin = k;
                minSch = sch;
                minMS = sch.getMakeSpan();
            }
            //    Scheduling = getScheduling(P, sch);
//            sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
            //this.printScheduling(Scheduling, sch);
        }
        this.Scheduling = this.getScheduling(P, minSch);  // harus mendefinisikan problem baru
        printScheduling(Scheduling, minSch);
        return minSch;
    }

    // -------- NEH -----------------------------------------

    // -------- Gupta1 --------------------------------------

    Double findMin(int i){
        Double minpt = P.Jobs[i].processing_time[0].doubleValue()+
                P.Jobs[i].processing_time[1].doubleValue();

        for (int k=1;k<P.MACHINE_NUM-1;k++) {
            Double pt = P.Jobs[i].processing_time[k].doubleValue()+
                    P.Jobs[i].processing_time[k+1].doubleValue();
            if (pt < minpt) minpt = pt;
        }
        return minpt;
    }

    Double e (int i) {
        if (P.Jobs[i].processing_time[0] < P.Jobs[i].processing_time[P.MACHINE_NUM-1]) {
            return 1.0;
        } return -1.0;
    }
    // slop index for Palmer
    public Double [] CalculateSlop1 (){
        Double [] As = new Double [P.JOB_NUM];
        for (int i=0; i<P.JOB_NUM; i++){
            As [i] = e(i) / findMin(i);
        }
        return As;
    }

    public Schedule Gupta1(){
        Schedule sch = new Schedule (-1, P.JOB_NUM);
        int [] ord;
        Double [] Slop = CalculateSlop1();

        for (int i=0; i<P.JOB_NUM;i++) {
            sch.ordering[i] = i;
        }

        ord = SortPartition(sch.ordering, P.JOB_NUM, Slop);
        // reverse ord

        for (int i=0;i<P.JOB_NUM;i++) {
            sch.ordering[i] = ord[P.JOB_NUM-1-i];
        }
        Scheduling = getScheduling (P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
//        this.printScheduling(Scheduling, sch);
        return sch;
    }

    // ------------------------------------------------------
    // -------- Gupta -----------------------------------------

    Double [] calP1(int k) {
        Double [] p1 = new Double [P.JOB_NUM];

        for (int i=0;i<P.JOB_NUM;i++) {
            p1[i] = 0.0;
            for (int m = 0; m < k; m++) {
                p1[i] = p1[i] + P.Jobs[i].processing_time[m];
            }
        }
        return p1;
    }

    Double [] calP2(int k) {
        Double [] p2 = new Double [P.JOB_NUM];

        for (int i=0; i < P.JOB_NUM; i++) {
            p2[i] = 0.0;
            for (int m = P.MACHINE_NUM-k; m < P.MACHINE_NUM; m++) {
                p2[i] = p2[i] + P.Jobs[i].processing_time[m];
            }
        }

        return p2;
    }

    Double R (int i, Double [] p1, Double [] p2){
        Double r;

        if (p1[i] >= p2[i]) {
            r = 1 / p2[i];
        }
        else {
            r = -1 /p1[i];
        }

        return r;
    }

    Double T (int k1, int m, Schedule sch) {
        Double res;

        if ((k1 < 0) || (m < 0)) {
            res = 0.0;
        }
        else {
            res = this.max(T(k1-1, m, sch), T(k1,m-1,sch))+P.Jobs[sch.ordering[k1]].processing_time[m];
        }
        return res;
    }

    public Schedule Gupta (){
        Schedule sch = new Schedule (-1,P.MACHINE_NUM);
        Double [][] PTM = new Double [P.MACHINE_NUM][P.JOB_NUM];
        int [][] sortedR = new int [P.JOB_NUM][P.MACHINE_NUM];

        for (int k=0;k<P.MACHINE_NUM-1;k++){
            Double [] p1 = calP1(k+1);
            Double [] p2 = calP2(k+2);
            for (int i=0; i<P.JOB_NUM;i++){
                PTM[k][i] = R(i,p1,p2);
            }

        }

        // untuk setiap k, urutkan job berdasarkan nilai R(i,k)
        // jika ada yang sama digunakan pedoman rumus rekursif T(k)
        int [][] sortedk = new int [P.MACHINE_NUM-1][P.JOB_NUM];
        // inisialisasi, saat awal terurut nomor job saja

        for (int i=0; i<P.MACHINE_NUM-1;i++){
            for (int j=0; j<P.JOB_NUM; j++) {
                sortedk[i][j] = j;
            }
        }

        for (int k=0; k<P.MACHINE_NUM-1; k++) {
            for (int i=0; i<P.JOB_NUM-1; i++){
                int min = i;
                for (int j = i+1; j<P.JOB_NUM; j++){
                    if (PTM[k][sortedk[k][j]] < PTM[k][sortedk[k][min]]){
                        min = j;
                    } else
                    if (PTM[k][sortedk[k][j]] == PTM[k][sortedk[k][min]]) {
                        // pilih dengan persamaan T
                        if (T(sortedk[k][j],k,sch) < T(sortedk[k][min], k, sch))
                            min = j;
                    }
                }
                if (min != i) {
                    int temp = sortedk[k][min];
                    sortedk[k][min] = sortedk[k][i];
                    sortedk[k][i] = temp;
                }
            }
        }

        // hitung makespan untuk setiap urutan di k
        int idxMin = -1;
        Double minMS = 0.0;
        Schedule minSch = new Schedule (-1, P.JOB_NUM);
        for (int k = 0; k < P.MACHINE_NUM-1; k++) {
            sch = new Schedule (k, P.JOB_NUM);
            System.arraycopy(sortedk[k], 0, sch.ordering, 0, P.JOB_NUM);
            Scheduling = getScheduling (P, sch);
            sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);

            if ((idxMin == -1) || (sch.getMakeSpan() <= minMS)){
                idxMin = k;
                minSch = sch;
                minMS = sch.getMakeSpan();
            }
        }

        this.printScheduling(Scheduling, minSch);
        minSch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        return minSch;
    }
    // -------- Gupta -----------------------------------------

    // --------- Palmer ---------------------------------------------
    Double Slopi (int j) {
        Double A = 0.0;

        for (int i=1; i <= P.MACHINE_NUM; i++){
            A = A - (P.MACHINE_NUM - (2*i -1)) * P.Jobs[j].processing_time[i-1];
        }
        return A;
    }
    // slop index for Palmer
    public Double [] CalculateSlop (){
        Double [] As = new Double [P.JOB_NUM];
        for (int i=0; i<P.JOB_NUM; i++){
            As [i] = Slopi (i);
        }
        return As;
    }

    public Schedule Palmer (){
        Schedule sch = new Schedule (-1, P.JOB_NUM);
        int [] ord;
        Double [] Slop = CalculateSlop();

        for (int i=0; i<P.JOB_NUM;i++) {
            sch.ordering[i] = i;
        }

        ord = SortPartition(sch.ordering, P.JOB_NUM, Slop);
        // reverse ord

        for (int i=0;i<P.JOB_NUM;i++) {
            sch.ordering[i] = ord[P.JOB_NUM-1-i];
        }
        Scheduling = getScheduling (P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
//        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // ------------------------------------------------------------------------

    // -------------------------- Dannenbring ---------------------------------

    public Schedule Dannenbring(){
        Schedule sch = new Schedule (-1, P.JOB_NUM);
        int [] p1 = new int [P.JOB_NUM+1];
        int [] p2 = new int [P.JOB_NUM+1];
        Double [] ptj1 = new Double [P.JOB_NUM];
        Double [] ptj2 = new Double [P.JOB_NUM];
        // menghitung waktu proses setiap job di mesin 1 dan mesin 2
        int idx1 = 0;
        int idx2 = 0;
        for (int i=0; i<P.JOB_NUM; i++) {
            ptj1[i] = 0.0;
            ptj2[i] = 0.0;
            for (int j=0; j<P.MACHINE_NUM; j++) {
                ptj1[i] = ptj1[i] + (P.MACHINE_NUM - j) * P.Jobs[i].processing_time[j];
                ptj2[i] = ptj2[i] + (j+1) * P.Jobs[i].processing_time[j];
            }
            if (ptj1[i] < ptj2[i]) {
                p1[idx1] = i;
                idx1++;
            } else {
                p2[idx2] = i;
                idx2++;
            }
        }
        p1[P.JOB_NUM] = idx1;
        p2[P.JOB_NUM] = idx2;

        int [] s1;
        s1 = SortPartition(p1, p1[P.JOB_NUM], ptj1);
        int [] s2;
        s2 = SortPartition(p2, p2[P.JOB_NUM], ptj2);

        System.arraycopy(s1, 0, sch.ordering, 0, p1[P.JOB_NUM]);

        int j = p1[P.JOB_NUM];
        for (int i=p2[P.JOB_NUM]; i > 0; i--) {
            sch.ordering[j] = s2[i-1];
            j++;
        }
        Scheduling = getScheduling(P, sch);
        sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
        this.printScheduling(Scheduling, sch);
        return sch;
    }
    // ------------------------------------------------------------------------

    // --------- Pour  ---------------------------------------------
    int [][] ordInit(){
        int [][] ord = new int [P.JOB_NUM][P.MACHINE_NUM];

        // urutan original, terurut berdasarkan kedatangan job
        for (int i=0; i< P.MACHINE_NUM; i++){
            for (int j=0;j<P.JOB_NUM; j++){
                ord [j][i] = j;
            }
        }

        for (int j = 0; j < P.MACHINE_NUM; j++) {
            for (int k=0; k < P.JOB_NUM-1; k++) {
                int min = k;
                for (int l = k+1; l < P.JOB_NUM; l++) {
                    // cari min ke k
                    if (P.Jobs[ord[l][j]].processing_time[j] < P.Jobs[ord[min][j]].processing_time[j] ) {
                        min = l;
                    }
                }

                if (min != k) { //swap
                    int s = ord[k][j];
                    ord[k][j] = ord[min][j];
                    ord[min][j] = s;
                }
            }
        }
        return ord;
    }

    void ordUpdate1(int pos, int job, int [] orgOrd){
        int j = pos;
        while (orgOrd[j] != job) { // selama bukan job
            j++;
        }
        // ditemukan posisi job
        // job akan dibawa naik ke urutan pos
        // berarti isi ord dari pos s/d j-1 ditarik turun satu posisi
        for (int k=j;k>pos;k--){
            orgOrd[k] = orgOrd[k-1];
        }
        orgOrd[pos] = job;
    }

    void ordUpdate(int [][] ord, int pos, int job){
        // ubah urutan di orgOrd
        for (int i=0; i<P.MACHINE_NUM;i++ ) { // untuk setiap mesin
            int j = pos;
            while (ord[j][i] != job) { // selama bukan job
                j++;
            }
            // ditemukan posisi job
            // job akan dibawa naik ke urutan pos
            // berarti isi ord dari pos s/d j-1 ditarik turun satu posisi
            for (int k=j;k>pos;k--){
                ord[k][i] = ord[k-1][i];
            }
            ord[pos][i] = job;
        }
    }

    public Schedule Pour1 (int pos, int [][] ordS, Schedule orgOrd) {
        Double [][] temp = new Double [P.JOB_NUM][P.MACHINE_NUM+1]; // penampung problem
        //int [][] ordS = ordInit(); //new int [P.JOB_NUM][P.MACHINE_NUM];
        int [][] ord = new int[P.JOB_NUM][P.MACHINE_NUM];

        Schedule opt = new Schedule(-1,P.JOB_NUM);
        Schedule minMS = new Schedule(-1,P.JOB_NUM);

        for (int i=pos; i<P.JOB_NUM; i++){ // harus disesuaikan dengan sisa job yang belum fixed urutannya
            // bisa acuannya ordS dan pos
            // schedule ini digunakan untuk menyimpan schedule dengan urutan job
            // yang sedang ditinjau
            // apa bedanya dengan opt dan minMS?

            Schedule sch = new Schedule (i, P.JOB_NUM);

            // menyalin apa ini?
            // ord menyimpan urutan job di setiap mesin sesuai dengan ordS
            // ordS input parameter
            for (int j=0;j<P.JOB_NUM;j++){
                System.arraycopy(ordS[j], 0, ord[j], 0, P.MACHINE_NUM);
            }

            // menempatkan job di urutan ke-pos
            // harus mengacu isi ord
            ordUpdate(ord, pos, orgOrd.ordering[i]);//    ord[i][0]); // menempatkan job pertama di urutan pertama setiap mesin

            // hitung completion time
            for (int k=0; k < P.MACHINE_NUM; k++) {
                temp[ord[0][k]][k] = 0.0;
                for (int l=1; l<P.JOB_NUM; l++) {
                    Double a = temp[ord[l-1][k]][k];
                    Double b = P.Jobs[ord[l][k]].processing_time[k];
                    temp[ord[l][k]][k] = a + b;
                    //temp[ord[l-1][k]][k];
                    //temp[ord[l][k]][k] = temp[ord[l][k]][k]+ P.Jobs[ord[l][k]].processing_time[k];
                }
            }
            // hitung total completion time untuk setiap job
            for (int k=0;k<P.JOB_NUM;k++) {
                Double sum = 0.0;
                for (int l=0; l<P.MACHINE_NUM;l++){
                    sum = sum + temp[k][l];
                }
                temp[k][P.MACHINE_NUM] = sum;
            }
            // cari urutan berdasarkan total completion time
            // simpan di schedule

            sch.setDefaultOrdering();
            // mengurutkan job berdasarkan processing-time nya
            for (int k=0; k < P.JOB_NUM-1; k++) {
                int min = k;
                for (int l = k+1; l < P.JOB_NUM; l++) {
                    // cari maks ke k
                    if (temp[sch.ordering[l]][P.MACHINE_NUM] < temp[sch.ordering[min]][P.MACHINE_NUM]) {
                        //if (temp[l][j] < temp[min][j]) {
                        min = l;
                    }
                }
                if (min != k) { //swap
                    int s = sch.ordering[k];
                    sch.ordering[k] = sch.ordering[min];
                    sch.ordering[min] = s;
                }
            }

            Scheduling = getScheduling (P, sch);
            // menjadi salah untuk penghitungan makespan, karena job terakhir belum tentu job_num-1
            sch.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);
            //this.printGanttChart(Scheduling, sch);

            if ((minMS.getId() == -1) || (sch.getMakeSpan() <= minMS.getMakeSpan())) {
                minMS.setId(sch.getId());
                minMS.setMakeSpan(sch.getMakeSpan());
                minMS.setOrdering(sch.getOrdering());
            }
        }
        minMS.printSchedule();
        // harus ada perubahan ordS, setelah ditentukan job yang fixed
        return minMS;
    }

    public Schedule Pour (){
        int [][] ordS = ordInit(); //new int [P.JOB_NUM][P.MACHINE_NUM];
        // ordS di saat awal menyimpan urutan job di setiap mesin terurut menaik sesuai waktu pemrosesan
        // setelah memanggil Pour1 ditetapkan job urutan pertama, sehingga urutan di ordS juga harus
        // diubah, disesuaikan
        Schedule opt = new Schedule(-1,P.JOB_NUM);
        Schedule orgOrd = new Schedule (-1, P.JOB_NUM);
        orgOrd.setDefaultOrdering();

        for (int i=0; i < P.JOB_NUM-1; i++) {
            // cari job yang tepat di urutan ke-i
            opt = Pour1(i, ordS, orgOrd);
            // sesuaikan ordS;
            ordUpdate1(i, opt.ordering[i], orgOrd.ordering);
            ordUpdate(ordS, i, opt.ordering[i]);    // update urutan per mesin
        }
        opt.setMakeSpan(Scheduling[P.JOB_NUM-1][P.MACHINE_NUM-1].finish);

        return opt;
    }

    public void genHeuristic(){
        Double [] pSlop = CalculateSlop();
        Double [] gSlop = CalculateSlop1();
        Double [] params = new Double [4];
        params [0] = new Double (P.MACHINE_NUM);
        params [1] = new Double (P.JOB_NUM);

        for (int i=0; i<P.JOB_NUM; i++) {
            // hitung sesuai program --> bagaimana menghubungkan dengan program?
        }
    }
}
