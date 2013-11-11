import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class ProbabilityCircuitDemo extends ProbabilityCircuit {

    public ProbabilityCircuitDemo(Reader reader) throws IOException {
        super(reader);
    }

    @Override
    public boolean[] logicalFunction() {
        // input size = 4
        // multiplexer and AND gate
        //output size = 1
        /*
         *     _____
         * ___|     |
         * ___|     |___________
         * ___|     |        ___D---
         *    |_____|       |
         *    ______________|
         */
        boolean[] inputs = this.getInputs();
        
        boolean multiplexer = false;
        if (this.getInputs()[0])
            multiplexer = inputs[1];
        else
            multiplexer = inputs[2];
        boolean[] outputs = {multiplexer && inputs[3]};
        this.setOutputs(outputs);
        return this.getOutputs();
    }
    
    public static void main(String args[]) {
        if (args == null || args.length == 0)
            throw new RuntimeException("Usage is: java ProbabilityCircuitDemo DEMO_INPUT");
        File file = new File(args[0]);
        try {
            Reader reader = new FileReader(file);
            ProbabilityCircuitDemo pcd = new ProbabilityCircuitDemo(reader);
            pcd.activate();
        }//try
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }//main
}//


