package demo16;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Employee implements Writable {
	// 需要的值
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	@Override
	public void readFields(DataInput input) throws IOException {
		// TODO Auto-generated method stub
		// 反序列化
		this.empno = input.readInt();
		this.ename = input.readUTF();
		this.job = input.readUTF();
		this.mgr = input.readInt();
		this.hiredate = input.readUTF();
		this.sal = input.readInt();
		this.comm = input.readInt();
		this.deptno = input.readInt();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		// 序列化
		output.writeInt(this.empno);
		output.writeUTF(this.ename);
		output.writeUTF(this.job);
		output.writeInt(this.mgr);
		output.writeUTF(this.hiredate);
		output.writeInt(this.sal);
		output.writeInt(this.comm);
		output.writeInt(this.deptno);
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
}
