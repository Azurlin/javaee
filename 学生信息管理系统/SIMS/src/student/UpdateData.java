package student;

public class UpdateData {
	public int row;
	public int column;
	public int row_id;
	public String column_name;
	public String data;
	public UpdateData(int row,int column,int row_id,String column_name,String data){
		this.row = row;
		this.column = column;
		this.data = data;
		this.row_id = row_id;
		this.column_name = column_name;
	}
	public int getrow(){
		return row;
	}
	public int getcolumn(){
		return column;
	}
	public String getdata(){
		return data;
	}
}
