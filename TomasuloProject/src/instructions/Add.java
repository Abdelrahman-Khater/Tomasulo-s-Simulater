package instructions;

import static instructions.Status.FINISHED;
import static instructions.Status.ISSUED;

public class Add extends Instruction {

    public Add(int destinationRegister, int sourceRegister1 , int sourceRegister2, int latency, InstructionListener listener, String assemblyInstruction) {
        super(destinationRegister, sourceRegister1, sourceRegister2, latency, listener, assemblyInstruction);
    }

    public void writeBack() {
        listener.onALU(getLabel(), vi + vj);
        setStatus(FINISHED);
    }

    public void issue() {
        setStatus(ISSUED);
        listener.issueALU(this);
    }

    public String toString() {
        return "ADD.D " + super.toString();
    }
    public String getInstruction() {
        return "ADD.D " + "F"+super.getDestinationRegister()+" "+"F"+super.getSourceRegister1()+" "+"F"+super.getSourceRegister2();
    }
    public String getType(){
        return "ADD.D ";
    }

    @Override
    public int getAddress() {
        return 0;
    }

    @Override
    public Add clone(){
        Add ret = new Add(this.destinationRegister,this.sourceRegister1,this.sourceRegister2,this.cyclesLeft,this.listener,this.assemblyInstruction);
        ret.label= this.label;
        ret.qi = this.qi;
        ret.qj = this.qj;
        ret.vi = this.vi;
        ret.vj = this.vj;
        ret.issueCycle = this.issueCycle;
        ret.setStatus(this.getStatus());
        ret.finishExecCycle = this.finishExecCycle;
        ret.startExecCycle = this.startExecCycle;
        ret.writeBackCycle = this.writeBackCycle;
        return ret;
    }
}
