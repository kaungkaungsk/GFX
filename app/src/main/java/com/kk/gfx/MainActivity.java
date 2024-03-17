package com.kk.gfx;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import java.io.*;
import android.provider.DocumentsContract;
import android.provider.DocumentsContract.Document;
import androidx.documentfile.provider.DocumentFile;
import java.net.URI;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class MainActivity extends AppCompatActivity {
	
	Uri muri;
	Uri desturi;
	Uri parenturi;
	Uri uri2;
	DocumentFile mfile;
	DocumentFile mfile1;
	DocumentFile parentfile;
	int NEW_FOLDER_REQUEST_CODE;
	private double ic = 0;
	Uri newUri;
	DocumentFile gjfhjk;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private CardView cardview5;
	private CardView cardview1;
	private LinearLayout linear3;
	private CardView cardview2;
	private LinearLayout layout_global;
	private CardView cardview3;
	private ImageView imageview3;
	private LinearLayout layout_korea;
	private CardView cardview4;
	private ImageView imageview4;
	private LinearLayout btn_active;
	private TextView textview1;
	
	private SharedPreferences sp;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		cardview5 = findViewById(R.id.cardview5);
		cardview1 = findViewById(R.id.cardview1);
		linear3 = findViewById(R.id.linear3);
		cardview2 = findViewById(R.id.cardview2);
		layout_global = findViewById(R.id.layout_global);
		cardview3 = findViewById(R.id.cardview3);
		imageview3 = findViewById(R.id.imageview3);
		layout_korea = findViewById(R.id.layout_korea);
		cardview4 = findViewById(R.id.cardview4);
		imageview4 = findViewById(R.id.imageview4);
		btn_active = findViewById(R.id.btn_active);
		textview1 = findViewById(R.id.textview1);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		ic = 0;
		final boolean[] move = {true};
		int LAYOUT_FLAG;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			    LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
		} else {
			    LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
		}
		final WindowManager.LayoutParams parm = new WindowManager.LayoutParams(
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        LAYOUT_FLAG,
		        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
		        PixelFormat.TRANSLUCENT
		);
		final View inflate = getLayoutInflater().inflate(R.layout.floating_view, null);
		parm.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		final WindowManager mana = (WindowManager) getSystemService(WINDOW_SERVICE);
		final ImageView logo = (ImageView) inflate.findViewById(R.id.logo);
		final LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		final TextView close = (TextView) inflate.findViewById(R.id.close);
		final androidx.cardview.widget.CardView base = (androidx.cardview.widget.CardView) inflate.findViewById(R.id.cardview1);
		final androidx.appcompat.widget.SwitchCompat login = (androidx.appcompat.widget.SwitchCompat) inflate.findViewById(R.id.login);
		final androidx.appcompat.widget.SwitchCompat lobby = (androidx.appcompat.widget.SwitchCompat) inflate.findViewById(R.id.lobby);
		final androidx.appcompat.widget.SwitchCompat delete = (androidx.appcompat.widget.SwitchCompat) inflate.findViewById(R.id.delete);
		//logo.setImageResource(R.drawable.app_icon);
		base.setVisibility(View.GONE);
		close.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFF44336));
		logo.setOnTouchListener(new View.OnTouchListener() {
			    private int x;
			    private int y;
			
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        switch (event.getAction()) {
					            case MotionEvent.ACTION_DOWN:
					                x = (int) event.getRawX();
					                y = (int) event.getRawY();
					                move[0] = false;
					                break;
					            case MotionEvent.ACTION_UP:
					                x = (int) event.getRawX();
					                y = (int) event.getRawY();
					                break;
					            case MotionEvent.ACTION_MOVE:
					                int nowX = (int) event.getRawX();
					                int nowY = (int) event.getRawY();
					                int movedX = nowX - x;
					                int movedY = nowY - y;
					                x = nowX;
					                y = nowY;
					                parm.x = parm.x + movedX;
					                parm.y = parm.y + movedY;
					                mana.updateViewLayout(inflate, parm);
					                move[0] = true;
					                break;
					            default:
					                break;
					        }
				        return false;
				    }
		});
		parm.gravity = Gravity.LEFT | Gravity.LEFT;
		parm.x = 0;
		parm.y = 0;
		if (android.provider.Settings.canDrawOverlays(getApplicationContext())) {
						            //mana.addView(inflate, parm);
						        } else {
						            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
						            startActivity(intent);
						        }
		logo.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View _view) {
				        if (!move[0]) {
					ic++;
					if (ic % 2 == 0) {
						base.setVisibility(View.GONE);
					}
					else {
						base.setVisibility(View.VISIBLE);
					}
				}
				    }
		});
		login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
								final boolean _isChecked = _param2;
				if (_isChecked) {
					if (!sp.getString("DIRECT_FOLDER_URI", "").equals("")) {
						try{
							Uri newUri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", "").concat("".replace("/", "%2F")));
							_CopyAssets(newUri, "fake.zip", "myfake.obb");
						}catch(Exception e){
							 
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Please select PUBG version and grant permission");
					}
				}
			}
		});
		lobby.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
								final boolean _isChecked = _param2;
				if (_isChecked) {
					if (!sp.getString("DIRECT_FOLDER_URI", "").equals("")) {
						try{
							Uri newUri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", "").concat("".replace("/", "%2F")));
							_CopyAssets(newUri, "fake.zip", "myfake.obb");
						}catch(Exception e){
							 
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Please select PUBG version and grant permission");
					}
				}
			}
		});
		delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
								final boolean _isChecked = _param2;
				if (_isChecked) {
					try{
						Uri newUri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", "").concat("myfake.obb".replace("/", "%2F")));
						_DeleteFile(newUri);
					}catch(Exception e){
						 
					}
				}
			}
		});
		layout_global.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				layout_global.setBackgroundColor(0xFFE0F2F1);
				layout_korea.setBackgroundColor(0xFFFFFFFF);
				try{
									Uri muri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", ""));
									    mfile = DocumentFile.fromTreeUri(MainActivity.this, muri);
									                    
									if (!mfile.canRead() || !mfile.canWrite()) {
												_givePermission("com.tencent.ig");
									}
									else {
												
									}
						}catch(Exception e){
									_givePermission("com.tencent.ig");
						}
			}
		});
		layout_korea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				layout_global.setBackgroundColor(0xFFFFFFFF);
				layout_korea.setBackgroundColor(0xFFE0F2F1);
				try{
									Uri muri = Uri.parse(sp.getString("DIRECT_FOLDER_URI", ""));
									    mfile = DocumentFile.fromTreeUri(MainActivity.this, muri);
									                    
									if (!mfile.canRead() || !mfile.canWrite()) {
												_givePermission("com.pubg.krmobile");
									}
									else {
												
									}
						}catch(Exception e){
									_givePermission("com.pubg.krmobile");
						}
			}
		});
		btn_active.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!sp.getString("DIRECT_FOLDER_URI", "").equals("")) {
					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
									    mana.addView(inflate, parm);
							} else {
									    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
											        if (android.provider.Settings.canDrawOverlays(getApplicationContext())) {
													            mana.addView(inflate, parm);
								btn_active.setEnabled(false);
													        } else {
													            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
													            startActivity(intent);
													        }
											    }
							}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please select PUBG version and grant permission");
				}
			}
		});
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				btn_active.setEnabled(true);
				try{
					mana.removeView(inflate);
				}catch(Exception e){
					 
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		    if (_resultCode == Activity.RESULT_OK) {
							            if (_data != null) {
												               muri = _data.getData();
												if (Uri.decode(muri.toString()).endsWith(":")) {
																	
																	 
												}
												else {
																	final int takeFlags = i.getFlags()
																	            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
																	            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
																	// Check for the freshest data.
																	getContentResolver().takePersistableUriPermission(muri, takeFlags);
																	sp.edit().putString("FOLDER_URI", muri.toString()).commit();
																	    mfile = DocumentFile.fromTreeUri(this, muri);
																	                    
																	    mfile1 = mfile.createFile("*/*", "test.file");
																	    uri2 = mfile1.getUri();
																	sp.edit().putString("DIRECT_FOLDER_URI", uri2.toString().substring((int)(0), (int)(uri2.toString().length() - 9))).commit();
																	try{
																						        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uri2);
																						     
																						        } catch (FileNotFoundException e) {
																						         
																						    }             
												}
												       } else {
												        
												   }
							       } else {
							       finish();
							finishAffinity();
							   }
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		sp.edit().remove("DIRECT_FOLDER_URI").commit();
	}
	public void _extra() {
		
		
	}
	public boolean copyFileFromAssets2(String filename, Uri targetUri)
	    {
				
				
					       
			        		InputStream fis = null;
					OutputStream fos = null;
			
					try {
								
					ContentResolver content = getApplicationContext().getContentResolver();
					            fis = this.getAssets().open(filename);
					            fos = content.openOutputStream(targetUri);
					
								byte[] buff = new byte[1024];
								int length = 0;
					
								while ((length = fis.read(buff)) > 0) {
											fos.write(buff, 0, length);
								}
					} catch (IOException e) {
								e.printStackTrace();
					} finally {
								if (fis != null) {
											try {
														fis.close();
											} catch (IOException e) {
														e.printStackTrace();
											}
								}
								if (fos != null) {
											try {
														fos.close();
									
											} catch (IOException e) {
														e.printStackTrace();
											}
								}
					}
			return true;
		}
	Boolean unzip (Uri _muri, DocumentFile _myDestFolder) {
			
			     Uri muri = _muri;
				  DocumentFile myFolder = null;
				DocumentFile mySubFolder = null;
				 DocumentFile mySubSubFolder = null;
			DocumentFile tempFile = null;
			
			try {
							try{
											InputStream is = getContentResolver().openInputStream(muri);
											BufferedInputStream bis = new BufferedInputStream(is);
											ZipInputStream zis = new ZipInputStream(bis);
											ZipEntry zipEntry;
											
											while ((zipEntry = zis.getNextEntry()) != null) {
															String fileName = null; 
															
															try {
																			fileName = zipEntry.getName();        
																			fileName = fileName.replace("\\",File.separator).replace("/",File.separator);
																			int p=fileName.lastIndexOf(File.separator);        
																			DocumentFile destFolder = _myDestFolder;
												                     //DocumentFile of the destination folder
																			String destName = fileName;
																			
																			if (p>=0) {
																							String[] split = fileName.split(File.separator);
																							
																							//If the .zip file contains multiple folder levels, this is where you  
																							//have to check and then create them, e.g. for 3 levels:
																							
																							
																							
																							if(split.length==1) {
															if(myFolder==null) {
																															myFolder = _myDestFolder;
																	
																											}
																											destFolder = myFolder;
																											destName = fileName;
																							} else if(split.length==2) {
															myFolder = _myDestFolder;
																											if(mySubFolder==null) {
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubFolder = myFolder.createDirectory(split[0]);
																				
																	}
																															
																											}
																											
																											destFolder = mySubFolder;
																											destName = split[1];
															
																							} else if(split.length==3) {
															
															myFolder = _myDestFolder;
																											if(mySubFolder==null) {
																	
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubFolder = myFolder.createDirectory(split[0]);
																				
																	}
																															
																											}
																											if(mySubSubFolder==null) {
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(mySubFolder.getUri().toString().concat(Uri.encode("/").concat(split[1]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubSubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubSubFolder = mySubFolder.createDirectory(split[1]);
																				
																	}
																															
																											}
																											
																											destFolder = mySubSubFolder;
																											destName = split[2];
																							}
																							
																							
																			}
											
											
											if (!zipEntry.isDirectory()) {
													
																					DocumentFile df = null;
																					
																					//Now you have to tell it what file extensions ("MIME" type) you want to use, e.g.:
													
													tempFile = null;
													tempFile = DocumentFile.fromSingleUri(this, Uri.parse(destFolder.getUri().toString().concat(Uri.encode("/").concat(destName))));
													
													
													if (tempFile.exists()) {
															
															df = tempFile;
															
															
																
													}else {
															df = destFolder.createFile("*/*",destName);
																
													}
																					
																					
																					OutputStream out = getContentResolver().openOutputStream(df.getUri());
																					BufferedOutputStream bos = new BufferedOutputStream(out);
																					long zipfilesize = zipEntry.getSize();
																					
																					byte[] buffer = new byte[10000];
																					int len = 0;
																					int totlen = 0;
																					
																					while (((len = zis.read(buffer, 0, 10000)) > 0) ) {
																									bos.write(buffer, 0, len);
																									totlen += len;
																					}
													
																					
																					bos.close();
											}
															} catch (IOException e1) {
																			
																	SketchwareUtil.showMessage(getApplicationContext(), e1.getMessage());		
																			
																			return false;
															}
											}
											
											is.close();
											bis.close();
											zis.close();
							} catch (IOException e2) {
							SketchwareUtil.showMessage(getApplicationContext(), e2.getMessage());
							
											return false;
							}
							
			} catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
							return false;
			}		           
			return true;
	}
	Boolean unzipAssets(String _filename, DocumentFile _myDestFolder) {
			
				  DocumentFile myFolder = null;
				  DocumentFile mySubFolder = null;
				 DocumentFile mySubSubFolder = null;
			DocumentFile tempFile = null;
			
			try {
							try{
											InputStream is = this.getAssets().open(_filename);
											BufferedInputStream bis = new BufferedInputStream(is);
											ZipInputStream zis = new ZipInputStream(bis);
											ZipEntry zipEntry;
											
											while ((zipEntry = zis.getNextEntry()) != null) {
															String fileName = null; 
															
															try {
																			fileName = zipEntry.getName();        
																			fileName = fileName.replace("\\",File.separator).replace("/",File.separator);
																			int p=fileName.lastIndexOf(File.separator);        
																			DocumentFile destFolder = _myDestFolder;
												                     //DocumentFile of the destination folder
																			String destName = fileName;
																			
																			if (p>=0) {
																							String[] split = fileName.split(File.separator);
																							
																							//If the .zip file contains multiple folder levels, this is where you  
																							//have to check and then create them, e.g. for 3 levels:
																							
																							
																							
																							if(split.length==1) {
															if(myFolder==null) {
																															myFolder = _myDestFolder;
																	
																											}
																											destFolder = myFolder;
																											destName = fileName;
																							} else if(split.length==2) {
															myFolder = _myDestFolder;
																											if(mySubFolder==null) {
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubFolder = myFolder.createDirectory(split[0]);
																				
																	}
																															
																											}
																											
																											destFolder = mySubFolder;
																											destName = split[1];
															
																							} else if(split.length==3) {
															
															myFolder = _myDestFolder;
																											if(mySubFolder==null) {
																	
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(myFolder.getUri().toString().concat(Uri.encode("/").concat(split[0]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubFolder = myFolder.createDirectory(split[0]);
																				
																	}
																															
																											}
																											if(mySubSubFolder==null) {
																	tempFile = null;
																	tempFile = DocumentFile.fromSingleUri(this, Uri.parse(mySubFolder.getUri().toString().concat(Uri.encode("/").concat(split[1]))));
																	
																	
																	
																	if (tempFile.exists()) {
																			
																			mySubSubFolder = tempFile;
																			
																			
																				
																	}else {
																			mySubSubFolder = mySubFolder.createDirectory(split[1]);
																				
																	}
																															
																											}
																											
																											destFolder = mySubSubFolder;
																											destName = split[2];
																							}
																							
																							
																			}
											
											
											if (!zipEntry.isDirectory()) {
													
																					DocumentFile df = null;
																					
																					//Now you have to tell it what file extensions ("MIME" type) you want to use, e.g.:
													
													tempFile = null;
													tempFile = DocumentFile.fromSingleUri(this, Uri.parse(destFolder.getUri().toString().concat(Uri.encode("/").concat(destName))));
													
													
													if (tempFile.exists()) {
															
															df = tempFile;
															
															
																
													}else {
															df = destFolder.createFile("*/*",destName);
																
													}
																					
																					
																					OutputStream out = getContentResolver().openOutputStream(df.getUri());
																					BufferedOutputStream bos = new BufferedOutputStream(out);
																					long zipfilesize = zipEntry.getSize();
																					
																					byte[] buffer = new byte[10000];
																					int len = 0;
																					int totlen = 0;
																					
																					while (((len = zis.read(buffer, 0, 10000)) > 0) ) {
																									bos.write(buffer, 0, len);
																									totlen += len;
																					}
													
																					
																					bos.close();
											}
															} catch (IOException e1) {
																			
																	SketchwareUtil.showMessage(getApplicationContext(), e1.getMessage());		
																			
																			return false;
															}
											}
											
											is.close();
											bis.close();
											zis.close();
							} catch (IOException e2) {
							SketchwareUtil.showMessage(getApplicationContext(), e2.getMessage());
							
											return false;
							}
							
			} catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
							return false;
			}		           
			return true;
	}
	{
	}
	
	
	public void _CopyAssets(final Uri _ttt, final String _AssetsFile, final String _RealFileName) {
		try{
				Uri muri = Uri.parse(_ttt.toString().concat(_RealFileName));
				    mfile = DocumentFile.fromTreeUri(this, muri);
				                    
				if (mfile.exists()) {
						try{
								        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);
								     
								        } catch (FileNotFoundException e) {
								         
								    }             
				}
				    parentfile = DocumentFile.fromTreeUri(this, _ttt);
				                    
				    mfile = parentfile.createFile("*/*", _RealFileName);
				    desturi = mfile.getUri();
				if (copyFileFromAssets2(_AssetsFile, desturi)) {
						     SketchwareUtil.showMessage(getApplicationContext(), "Done!");
						    } else {
						     SketchwareUtil.showMessage(getApplicationContext(), "Error ");
				}
		}catch(Exception e){
				SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
		}
	}
	
	
	public void _UnZip(final Uri _Kkkk, final String _zipname) {
		    mfile = DocumentFile.fromTreeUri(this, _Kkkk);
		                    
		if (unzipAssets (_zipname, mfile)) {
				SketchwareUtil.showMessage(getApplicationContext(), "ZIP SUCCESS 😝");
		}
		else {
				SketchwareUtil.showMessage(getApplicationContext(), "ERROR ");
		}
	}
	
	
	public void _DeleteFile(final Uri _delete) {
		try{
				    gjfhjk = DocumentFile.fromTreeUri(this, _delete);
				                    
				if (gjfhjk.exists()) {
						try{
								        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), _delete);
								     SketchwareUtil.showMessage(getApplicationContext(), "Delete OBB Success!");
								        } catch (FileNotFoundException e) {
								         SketchwareUtil.showMessage(getApplicationContext(), "FIX ");
								    }             
				}
		}catch(Exception e){
				SketchwareUtil.showMessage(getApplicationContext(), e.getMessage());
		}
	}
	
	
	public void _givePermission(final String _packagename) {
		i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
		Uri muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata/document/primary%3AAndroid%2Fobb%2F".concat(_packagename));
		    i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, muri);
		        startActivityForResult(i, NEW_FOLDER_REQUEST_CODE);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}