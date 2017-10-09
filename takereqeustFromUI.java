package com.sagipl.nsdl.production;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sagipl.nsdl.production.authentication.DBGstnConnection;
import com.sagipl.nsdl.production.authentication.GstinAuthDetailModel;
import com.sagipl.nsdl.production.authentication.GstnAuthentication;

@Path("/gstservice")
public class GSTWebService {
	@SuppressWarnings("unchecked")
	@Path("/login")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogin(String rqstData) {
		System.out.println("web sevice login method called request data is -------------------"+rqstData);
		JSONObject resObject = new JSONObject();
		try {	
			if (rqstData != null) {
				JSONObject rqstObject = (JSONObject) new JSONParser().parse(rqstData);
				String userName = rqstObject.get("username") != null ? rqstObject.get("username").toString() : null;
				if (userName != null) {
					JSONObject ob = checkUser(userName);
					if (ob != null) {
						resObject.put("code", "1");
						resObject.put("msg", ob);
						System.out.println(resObject);

					} else {
						resObject.put("code", "0");
						resObject.put("msg", "Invalid UserName");
						System.out.println(resObject);
					}
				} else {
					resObject.put("code", "0");
					resObject.put("msg", "userName is Null");
				}
			} else {
				System.out.println("Request Data is Null");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resObject.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Path("/upload")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(String rqstData ) throws Exception {
		JSONObject resObject = new JSONObject();
		String fileName;
		String data;
		String userName;
		String rqstType;
		String rtrnPeriod;
		String otpFlag;
		String otpNumber;
		JSONObject resObjectAuth=null;
		try {	
			if (rqstData != null) {
				JSONObject rqstObject = (JSONObject) new JSONParser().parse(rqstData);
				fileName = rqstObject.get("returnFileName") != null ? rqstObject.get("returnFileName").toString() : null;
				data = rqstObject.get("data") != null ? rqstObject.get("data").toString() : null;
				System.out.println("Data Payload----"+data);
				userName = rqstObject.get("loginUsrNm") != null ? rqstObject.get("loginUsrNm").toString() : null;
				rqstType = rqstObject.get("rqstType") != null ? rqstObject.get("rqstType").toString() : null;
				rtrnPeriod = rqstObject.get("rtrnPeriod") != null ? rqstObject.get("rtrnPeriod").toString() : null;
				otpFlag = rqstObject.get("otpFlag") !=null ? rqstObject.get("otpFlag").toString() :null;    //T or F flag used
				otpNumber = rqstObject.get("otpNumber") !=null ? rqstObject.get("otpNumber").toString() : null;
				
				if(userName !=null && !userName.isEmpty()) {
					GstnAuthentication gstAuth = new GstnAuthentication();
					GstinAuthDetailModel rqstDetailModel = new GstinAuthDetailModel();
					if(rqstType !=null && (rqstType.equalsIgnoreCase("upload") ||rqstType.equalsIgnoreCase("submit")) ) {
						System.out.println("---------Calling For Upload API------");
						if(data !=null && !data.isEmpty()) {
							rqstDetailModel.setData(data);
							String retPeriod = rqstObject.get("rtrnPeriod") !=null ? rqstObject.get("rtrnPeriod").toString():null;
							rqstDetailModel.setRetPeriod(retPeriod);
							rqstDetailModel.setRqstType(rqstType);
							rqstDetailModel.setUsername(userName);
							if(userName.equalsIgnoreCase("sag.tn.1") || userName.equalsIgnoreCase("sag.tn.2") || userName.equalsIgnoreCase("sag.mh.1") || userName.equalsIgnoreCase("sag.mh.2")) {
								rqstDetailModel.setGspname("gstn");
							} else {
								rqstDetailModel.setGspname("nsdl");
							}
							
							rqstDetailModel.setOtpFlag(otpFlag);
							rqstDetailModel.setOtpNumber(otpNumber);
							rqstDetailModel.setReturnFileName(fileName);
							
							resObject= gstAuth.gstinAuthentication(rqstDetailModel);
							return resObject.toString();				
						} else {
							resObject.put("code", "0");      
							resObject.put("key", "upload");
							resObject.put("error", "Data is Missing");
							return resObject.toString();
						}
						
					} else if(rqstType !=null && rqstType.equalsIgnoreCase("get")) {
						System.out.println("---------Calling For Get API------");
						String sectionName = rqstObject.get("sectionName")!=null ? rqstObject.get("sectionName").toString():null;
						String retPeriod = rqstObject.get("rtrnPeriod") !=null ? rqstObject.get("rtrnPeriod").toString():null;
						String returnFileType = rqstObject.get("returnFileName") !=null ? rqstObject.get("returnFileName").toString():null;
						if((sectionName !=null && !sectionName.isEmpty()) && (retPeriod !=null && !retPeriod.isEmpty())) {
							rqstDetailModel.setReturnFileName(returnFileType);
							rqstDetailModel.setRetPeriod(retPeriod);
							rqstDetailModel.setSectionName(sectionName);
							rqstDetailModel.setRqstType(rqstType);
							rqstDetailModel.setUsername(userName);
							if(userName.equalsIgnoreCase("sag.tn.1") || userName.equalsIgnoreCase("sag.tn.2") || userName.equalsIgnoreCase("sag.mh.1") || userName.equalsIgnoreCase("sag.mh.2")) {
								rqstDetailModel.setGspname("gstn");
							} else {
								rqstDetailModel.setGspname("nsdl");
							}
							rqstDetailModel.setOtpFlag(otpFlag);
							rqstDetailModel.setOtpNumber(otpNumber);
							rqstDetailModel.setReturnFileName(fileName);
							
							resObject= gstAuth.gstinAuthentication(rqstDetailModel);
							return resObject.toString();
						} else {
							resObject.put("code", "0");
							resObject.put("error", "Invalid Input Param");
							resObject.put("key", "rqst");
							return resObject.toString();
						}
						
					}
					
				} else {
					resObject.put("code", "0");
					resObject.put("error", "User Name is Null");
					resObject.put("key", "rqst");
				}
				System.out.println("Response From Server------------ "+resObjectAuth);
			}
		} catch (Exception e) {
			resObject.put("code", "0");
			resObject.put("error", e.toString());
			resObject.put("key", "rqst");
			e.printStackTrace();
		}
		return resObject.toString();
	}
	
	
	@Path("/gstinList")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getGstinList() {
		JSONArray arry  = getGstinListFromDB();
		return arry.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	@Path("/deleteContent")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String createDeleteFile(String data) {
		JSONObject resObject = new JSONObject();
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(data);
			System.out.println("Json Object------- "+obj.get("data"));
			JSONObject objDelete = createDeleteJSON((JSONObject)new JSONParser().parse(obj.get("data").toString()));
			if(objDelete !=null) {
				resObject.put("code", "1");
				resObject.put("key", "deleted");
				resObject.put("data", objDelete);
			} else {
				resObject.put("code", "0");
				resObject.put("key", "deleted");
				resObject.put("error", "Data is Null");
			}
		} catch(Exception e) {
			resObject.put("code", "0");
			resObject.put("key", "deleted");
			resObject.put("error", e.getMessage());
		}
		return resObject.toString();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Path("/statusReport")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getStatusReport(String rqstData) throws ParseException {
		JSONArray arry = new JSONArray();
		JSONObject resObj = new JSONObject();
		JSONObject rqstObject = (JSONObject) new JSONParser().parse(rqstData);
		String gstin = rqstObject.get("gstin")!=null ? rqstObject.get("gstin").toString():null;
		String returnPeriod = rqstObject.get("ret_period")!=null ? rqstObject.get("ret_period").toString():null;
		if(gstin !=null && returnPeriod !=null) {
			arry  = getRefStatus(gstin, returnPeriod);
			resObj.put("code", "1");
			resObj.put("data", arry);
		} else {
			resObj.put("code", "0");
			resObj.put("data", "Request Param is null");
		}
		return resObj.toString();
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject checkUser(String userName) {
		JSONObject resObj = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBGstnConnection.getConnection();
			pstmt = con.prepareStatement(
					"select gstauth_gstin,gstauth_state_cd from txn_gstauth_info where gstauth_usrid=?");
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					resObj = new JSONObject();
					resObj.put("gstin", rs.getString("gstauth_gstin"));
					resObj.put("stateCode", rs.getString("gstauth_state_cd"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resObj;
	}
	
	@SuppressWarnings({"unused" })
	private JSONObject insertRecord(JSONObject userData){
		JSONObject resObj = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBGstnConnection.getConnection();
			String sql = "";
			pstmt = con.prepareStatement(sql);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray getGstinListFromDB(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst  =null;
		JSONArray arry = new JSONArray();
		try {
			conn = DBGstnConnection.getConnection();
			pstmt = conn.prepareStatement("select distinct gstn_no from mst_gstn");
			rst = pstmt.executeQuery();
			while(rst.next()) {
				arry.add(rst.getString("gstn_no"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return arry;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getRefStatus(String gstin, String retPeriod ){
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			String query = "select refstatus_id,ref_tmstamp,ref_status from txn_gstref_status"
					+ " where ref_retperiod = ? and ref_gstin = ? order by ref_id desc limit 5";
			conn = DBGstnConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,retPeriod);
			pstmt.setString(2,gstin);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				jsonObj = new JSONObject();
				jsonObj.put("RefID",rs.getString("refstatus_id"));
				jsonObj.put("Time Stamp",rs.getString("ref_tmstamp"));
				jsonObj.put("Status Report",rs.getString("ref_status"));
				jsonArr.add(jsonObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonArr;
	}
	
	
	public static JSONObject createDeleteJSON(JSONObject object) {
		if(object !=null) {
			if(object.containsKey("b2b")) {
				JSONArray arryB2B = (JSONArray) object.get("b2b");
					for(int i=0; i<arryB2B.size();i++) {
						JSONObject b2bObj = (JSONObject) arryB2B.get(i);
						if(b2bObj.containsKey("cfs")) {
							b2bObj.remove("cfs");
						}
						JSONArray invArray = (JSONArray) b2bObj.get("inv");
						for(int j=0;j<invArray.size();j++) {
							JSONObject invObj = (JSONObject) invArray.get(j);
							if(invObj.containsKey("flag")) {
								invObj.replace("flag", "D");
							}
							if(invObj.containsKey("updby")) {
								invObj.remove("updby");
							}
							if(invObj.containsKey("cflag")) {
								invObj.remove("cflag");
							}
							if(invObj.containsKey("pos")) {
								invObj.remove("pos");
							}
							if(invObj.containsKey("rchrg")) {
								invObj.remove("rchrg");
							}
							if(invObj.containsKey("inv_typ")) {
								invObj.remove("inv_typ");
							}
							if(invObj.containsKey("val")) {
								invObj.remove("val");
							}
							if(invObj.containsKey("itms")) {
								invObj.remove("itms");
							}
						}
					}
				
			} else if(object.containsKey("b2cs")) {
				JSONArray b2csArray = (JSONArray) object.get("b2cs");
				for(int i=0;i<b2csArray.size();i++) {
					JSONObject b2csObj = (JSONObject) b2csArray.get(i);
					if(b2csObj.containsKey("flag")) {
						b2csObj.replace("flag", "D");
					} else {
						b2csObj.put("flag", "D");
					}
				}
			} else if(object.containsKey("hsn")) {
				JSONObject hsnObj = (JSONObject) object.get("hsn");
				if(hsnObj.containsKey("flag")) {
					hsnObj.replace("flag", "D");
				} else {
					hsnObj.put("flag", "D");
				}
			} else if(object.containsKey("b2cl")) {
				
			}
			else if(object.containsKey("cdnr")) {
				
			}
			else if(object.containsKey("cdnur")) {
				
			} else if(object.containsKey("at")) {
				
			} else if(object.containsKey("txpd")) {
				
			} else if(object.containsKey("doc_issue")) {
				
			}
		}
		return object;
	}
	
}
