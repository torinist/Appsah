package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the master_memcat database table.
 * 
 */
@Entity
@Table(name="master_memcat")
@NamedQuery(name="MasterMemcat.findAll", query="SELECT m FROM MasterMemcat m")
public class MasterMemcat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String name;

	//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="masterMemcat")
	private List<Member> members;

	public MasterMemcat() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getDelflag() {
		return this.delflag;
	}

	public void setDelflag(byte delflag) {
		this.delflag = delflag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setMasterMemcat(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setMasterMemcat(null);

		return member;
	}

}