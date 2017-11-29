package vault.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Keys")
public class KeyWrapper {
	
	private List<Key> keys;
	
	@XmlElement(name = "keys")
	public List<Key> getKeys() {
		return keys;
	}
	
	public void setKeys(List<Key> key) {
		this.keys = key;
	}
}
