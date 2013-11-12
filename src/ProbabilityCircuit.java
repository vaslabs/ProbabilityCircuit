import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public abstract class ProbabilityCircuit extends Circuit {

    private List<Double> logicalInputProbabilities;
    
    public ProbabilityCircuit(Reader reader) throws IOException {
        String line;
        BufferedReader br = null; 
        try {
            br = new BufferedReader(reader);
            
            logicalInputProbabilities = new ArrayList<Double>();
            while ( (line = br.readLine()) != null ) {
                double probabilityBeingActive = Double.parseDouble(line);
                logicalInputProbabilities.add(probabilityBeingActive);            
            }//while
            boolean[] inputs = new boolean[logicalInputProbabilities.size()];
            this.setInputs(inputs);
        }//try
        finally {
            if (br != null)
                br.close();
        }
    }//ProbabilityCircuit
    
    public void generateInputs() {
        
        for (int i = 0; i < logicalInputProbabilities.size(); i++) {
            boolean state = Math.random() < logicalInputProbabilities.get(i);
            this.setInput(i, state);
        }//for
        
    }//generateInputs
    
}

abstract class Circuit implements Logical {
    private boolean[] inputs;
    private boolean[] outputs;
    
    public abstract boolean[] logicalFunction();
    public abstract void generateInputs();
    
    public void setInputs(boolean... inputs) {
        this.inputs = inputs;
    }//setInputs
    
    public void setInput(int i, boolean state) {
        inputs[i] = state;
    }
    
    public boolean[] getInputs() {
        return this.inputs;
    }
    
    public boolean[] getOutputs() {
        return this.outputs;
    }
    
    public void activate() {
        this.generateInputs();
        outputs = logicalFunction();
        this.generateReport();
    }//activate
    
    public void generateReport() {
        for (int i = 0; i < inputs.length || i < outputs.length; i++) {
            if (i >= inputs.length)
                System.out.print(" ------>");
            else
                System.out.print(inputs[i] + "------>");
            if (i >= outputs.length)
                System.out.println();
            else
                System.out.println(outputs[i]);
        }//for
    }//generateReport
}

interface Logical {

    boolean[] logicalFunction();
}
