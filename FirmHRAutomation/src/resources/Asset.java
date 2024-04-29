package resources;

import Exceptions.InvalidAssetsException;

public class Asset {
	
	private String assetId;
	private String assetName;
	private String assetExpiry;
	
	public Asset(String assetId, String assetName, String assetExpiry) throws InvalidAssetsException {
		this.setAssetId(assetId);
		this.assetName = assetName;
		this.assetExpiry = assetExpiry;
	}
	
	public String getAssetId() {
		return this.assetId;
	}
	
	public void setAssetId(String assetId){
		if(this.isValidAssetID(assetId)) {			
			this.assetId = assetId;
		}
		else {
			this.assetId = null;
		}
	}
//	public void setAssetId(String assetId){
//		if(this.isValidAssetID(assetId)) {			
//			this.assetId = assetId;
//		}
//	}
	
	public String getAssetName() {
		return this.assetName;
	}
	
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	public String getAssetExpiry() {
		return this.assetExpiry;
	}
	
	public void setAssetExpiry(String assetExpiry) {
		this.assetExpiry = assetExpiry;
	}
	
	private boolean isValidAssetID(String assetId) {
		String regEx = "(DSK|LTP|IPH)-[\\d]{6}([Hh]|[Ll])";
		if(assetId.matches(regEx))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Asset Id: "+getAssetId()+", Asset Name: "+getAssetName()+", Asset Expiry: "+getAssetExpiry();
	}
}
