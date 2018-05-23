package com.inx.customreusablecomponent.storagemanager;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by keerthana on 18/5/18.
 */

public class StorageManager {

    private static StorageManager storageManagerInstance = null;
    public final String PROJECT_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + getApplicationName();
    public static Uri imageUri, videoUri;
    public static String IMAGE_DIRECTORY = "/images";
    public static String VIDEO_DIRECTORY = "/videos";
    public static String IMAGE_FILE_FORMATE = ".png";
    public static String VIDEO_FILE_FORMATE = ".mp4";
    public static String IMAGE_PREFIX = "Img-";
    public static String VIDEO_PRFIX = "Vid-";
    public static String APPLICATION_NAME = "";
    public static Context mContext;
    PackageInfo packageInfo;

    private StorageManager() {

    }

    /**
     * Crate the instance for storage manager
     * @param context
     * @return
     */
    public static synchronized StorageManager getInstance(Context context) {
        mContext = context;
        if (storageManagerInstance == null) {
            storageManagerInstance = new StorageManager();
        }
        return storageManagerInstance;
    }

    /**
     * Create the image directory and create a new image file inside it, Also return that file URI
     * @param imageDirectoryName
     * @return uri
     */
    public Uri getImageUri(String imageDirectoryName) {

        try {
            File parentFolder = new File(PROJECT_DIRECTORY);
            File imageFolder = new File(PROJECT_DIRECTORY + (imageDirectoryName.isEmpty() ? IMAGE_DIRECTORY : "/" + imageDirectoryName));
            if (!parentFolder.exists() && !parentFolder.isDirectory()) {
                parentFolder.mkdir();
                imageFolder.mkdir();
            } else if (!imageFolder.exists() && !imageFolder.isDirectory()) {
                imageFolder.mkdir();
            }

            String fileName = IMAGE_PREFIX + System.currentTimeMillis() + IMAGE_FILE_FORMATE;
            File image = new File(imageFolder.getPath(), fileName);
            imageUri = Uri.fromFile(image);

//
//            Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//            startActivityForResult(i, Constants.REQUEST_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageUri;
    }

    /**
     * Create the image directory and create a new image file inside it, Also return that file URI
     * @param imageDirectoryName
     * @param imageFileFormat
     * @return uri
     */
    public Uri getImageUri(String imageDirectoryName, String imageFileFormat) {


        try {
            File parentFolder = new File(PROJECT_DIRECTORY);
            File imageFolder = new File(PROJECT_DIRECTORY + (imageDirectoryName.isEmpty() ? IMAGE_DIRECTORY : "/" + imageDirectoryName));
            if (!parentFolder.exists() && !parentFolder.isDirectory()) {
                parentFolder.mkdir();
                imageFolder.mkdir();
            } else if (!imageFolder.exists() && !imageFolder.isDirectory()) {
                imageFolder.mkdir();
            }

            String fileName = IMAGE_PREFIX + System.currentTimeMillis() + (imageFileFormat.isEmpty() ? IMAGE_FILE_FORMATE : imageFileFormat);
            File image = new File(imageFolder.getPath(), fileName);
            imageUri = Uri.fromFile(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageUri;
    }

    /**
     * Create the video directory and create a new video file inside it, Also return that file URI
     * @param videoDirectoryName
     * @return uri
     */
    public Uri getVideoUri(String videoDirectoryName) {

        try {


            File parentFolder = new File(PROJECT_DIRECTORY);
            File imageFolder = new File(PROJECT_DIRECTORY + (videoDirectoryName.isEmpty() ? IMAGE_DIRECTORY : "/" + videoDirectoryName));
            if (!parentFolder.exists() && !parentFolder.isDirectory()) {
                parentFolder.mkdir();
                imageFolder.mkdir();
            } else if (!imageFolder.exists() && !imageFolder.isDirectory()) {
                imageFolder.mkdir();
            }

            String fileName = VIDEO_PRFIX + System.currentTimeMillis() + VIDEO_FILE_FORMATE;
            File image = new File(imageFolder.getPath(), fileName);
            videoUri = Uri.fromFile(image);

//            Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            i.setType("*/*");
//            i.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
//            startActivityForResult(i, Constants.REQUEST_VIDEO_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return videoUri;
    }

    /**
     * Create the video directory and create a new video file inside it, Also return that file URI
     * @param videoDirectoryName
     * @param videoFileFormat
     * @return uri
     */
    public Uri getVideoUri(String videoDirectoryName, String videoFileFormat) {
        try {


            File parentFolder = new File(PROJECT_DIRECTORY);
            File imageFolder = new File(PROJECT_DIRECTORY + (videoDirectoryName.isEmpty() ? IMAGE_DIRECTORY : "/" + videoDirectoryName));
            if (!parentFolder.exists() && !parentFolder.isDirectory()) {
                parentFolder.mkdir();
                imageFolder.mkdir();
            } else if (!imageFolder.exists() && !imageFolder.isDirectory()) {
                imageFolder.mkdir();
            }

            String fileName = VIDEO_PRFIX + System.currentTimeMillis() + (videoFileFormat.isEmpty() ? VIDEO_DIRECTORY : videoFileFormat);
            File image = new File(imageFolder.getPath(), fileName);
            videoUri = Uri.fromFile(image);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return videoUri;
    }

    /**
     * Get the Actual file path from the URI
     * @param context
     * @param uri
     * @return uri
     * @throws URISyntaxException
     */
    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Check Whether it is a ExternalStorage Document from URI
     *
     * @param uri
     * @return boolean
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * Check Whether it is a Downloaded Document from URI
     *
     * @param uri
     * @return  boolean
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * Check Whether it is a Media Document from URI
     *
     * @param uri
     * @return boolean
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    /**
     * Get Image Uri from Bitmap image
     *
     * @param bitmap input bitmap image
     * @param context context of calling method;
     * @return imageUri as String
     */

    public static String getImageUri(Bitmap bitmap, Context context) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        Uri tempUri = Uri.parse(path);
        Cursor cursor = context.getContentResolver().query(tempUri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    /**
     * get custom size bitmap image  from file uri
     * @param context  context of calling method;
     * @param path input file path
     * @param reqWidth height of the image
     * @param reqHeight width of the image
     * @return bitmap image
     * @throws FileNotFoundException
     */

    public static Bitmap getDecodedBitmap(Context context, Uri path, int reqWidth, int reqHeight) throws FileNotFoundException {
        int height, width;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream is = context.getContentResolver().openInputStream(path);
        BitmapFactory.decodeStream(is, null, options);
        int samplesize = 1;

        height = options.outHeight;
        width = options.outWidth;
        if (height > reqHeight && width > reqWidth) {
            height = height / 2;
            width = width / 2;

            while ((height / samplesize) > reqHeight && (width / samplesize) > reqWidth) {
                samplesize *= 2;
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = samplesize;
        is = context.getContentResolver().openInputStream(path);
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * Get the name of the application
     *
     * @return application name
     */
    public String getApplicationName() {
        ApplicationInfo applicationInfo = mContext.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : mContext.getString(stringId);
    }

    /**
     * Get the current time stamp
     * @return time stamp
     */
    public String getCurrentTimeStamp()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        return ts;
    }

}
