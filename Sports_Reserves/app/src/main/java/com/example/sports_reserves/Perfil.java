package com.example.sports_reserves;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sports_reserves.entities.PerfilUsuario;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Perfil extends AppCompatActivity {

    ImageView foto;
    Button btnGuardar, btnCamara, btnGaleria;
    EditText txtNombre2, txtApellidos2, txtEdad, txtPeso, txtAltura, txtFecha, txtGenero;
    ImageButton btnAtras;
    AppPreferences preferences;
    public static PerfilUsuario perfilUsuario;
    int REQUEST_CODE=200;


    //Variables estáticas para el sharedpreferences
    static final String NOMBRE_FICHERO = "DATOS";
    static final String ETIQUETA_CONTRA = "CONTRA";
    static final String ETIQUETA_FOTO = "FOTO";
    static final String ETIQUETA_CONTRA_GUARDADA = "BOOLEAN";

    //Variables para usar las fotos y los permisos
    private static final int VENGO_DE_GALERIA = 100;
    private static final int PEDI_PERMISO_ESCRITURA = 1;
    private static final int VENGO_DE_CAMARA_CON_CALIDAD = 2;
    private static final long TIEMPO_REFRESCO = 10000;
    Uri imagenUri;

    //Fichero para poder usar la foto
    private File fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        foto = findViewById(R.id.foto);
        txtNombre2 = findViewById(R.id.txtNombre2);
        txtApellidos2 = findViewById(R.id.txtApellidos2);
        txtEdad = findViewById(R.id.txtEdad);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        txtFecha = findViewById(R.id.txtFecha);
        txtGenero = findViewById(R.id.txtGenero);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCamara = findViewById(R.id.btnCamara);
        btnGaleria = findViewById(R.id.btnGaleria);
        btnAtras = findViewById(R.id.btn_Atras);

        preferences = new AppPreferences(getSharedPreferences("local", MODE_PRIVATE));

        perfilUsuario = null;

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foto.getDrawable()!=null){
                    Perfil.ActualizarPerfil actualizarPerfil = new Perfil.ActualizarPerfil();
                    actualizarPerfil.execute();
                }
                else{
                    Toast.makeText(Perfil.this, "Debes echarte una foto", Toast.LENGTH_SHORT).show();
                }
                /*SharedPreferences preferencias=getSharedPreferences("Datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor Obj_Editor=preferencias.edit();
                Obj_Editor.putString("nombre",txtNombre2.getText().toString());
                Obj_Editor.putString("apellidos",txtApellidos2.getText().toString());
                Obj_Editor.putString("edad",txtEdad.getText().toString());
                Obj_Editor.putString("peso",txtPeso.getText().toString());
                Obj_Editor.putString("altura",txtAltura.getText().toString());
                Obj_Editor.putString("fecha",txtFecha.getText().toString());
                Obj_Editor.putString("genero",txtGenero.getText().toString());
                Obj_Editor.commit();*/

                //Toast.makeText(Perfil.this, "Datos guardados", Toast.LENGTH_SHORT).show();

            }
        });
        /*
        //Doy permisos para que la cámara pueda ver el fichero que he creado
        StrictMode.VmPolicy.Builder builder= new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        //Ponemos que al cargar nos compruebe si tenemos una foto ya en sharedpreferences para que nos la guarde y nos coja la contraseña
        SharedPreferences misDatos = getSharedPreferences(NOMBRE_FICHERO, MODE_PRIVATE);
        String ficheros=(misDatos.getString(ETIQUETA_FOTO,null));
        Log.d("Depurando", ""+ficheros);
        if(ficheros!=null){
            File fichero_foto= new File(ficheros);
            Bitmap rotacion= BitmapFactory.decodeFile(fichero_foto.getAbsolutePath());
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(fichero_foto.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            int grados=0;

            Matrix matrix= new Matrix();
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    grados=90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    grados=180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    grados=270;
                    break;
                default:
                    break;
            }
            foto.setImageBitmap(rotacion);
            foto.setRotation(grados);


        }
        //Configuramos el botón de la foto para que al darle nos permita hacernos una foto
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedirpermisoParaFoto();
            }
        });

         */
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cargarImagen();
                }
            }
        });
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    abrirCamara();
                }
            }
        });

        Perfil.obtenerPerfil obtenerPerfil = new Perfil.obtenerPerfil(preferences.getUsername());
        obtenerPerfil.execute();
    }

    private void mostrarPerfil() {
        if (perfilUsuario != null) {
            txtNombre2.setText(perfilUsuario.getNombre());
            txtApellidos2.setText(perfilUsuario.getApellidos());
            txtEdad.setText(perfilUsuario.getEdad());
            txtPeso.setText(perfilUsuario.getPeso());
            txtAltura.setText(perfilUsuario.getAltura());
            txtFecha.setText(perfilUsuario.getFecha_nacimiento());
            txtGenero.setText(perfilUsuario.getGenero());
            foto.setImageBitmap(decodificarImagen(perfilUsuario.getImagen()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarImagen() {
        int permisoAlmacenamiento= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (permisoAlmacenamiento==PackageManager.PERMISSION_GRANTED){
            startActivityForResult(intent, VENGO_DE_GALERIA);
        }
        else {
            Log.v("galeria","galeria");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == VENGO_DE_GALERIA) {
                Uri path = data.getData();
                foto.setImageURI(path);
            } else if (requestCode == 1) {
                Bundle extras = data.getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                foto.setImageBitmap(imgBitmap);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void abrirCamara() {
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }*/
        int permisoCamara= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (permisoCamara==PackageManager.PERMISSION_GRANTED){

            startActivityForResult(intent, 1);
        }
        else {
            Log.v("camara","camara");
            requestPermissions(new String[]{Manifest.permission.CAMERA},100);
        }

    }

    private class ActualizarPerfil extends AsyncTask<Void, Void, Void> {

        String dbMessage = "";

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                String imagenCodificada = codificarImagen(foto.getDrawable());
                //imagenCodificada = imagenCodificada.replace("\n", "");
                Log.v("img", String.valueOf(imagenCodificada.length()));
                String username = preferences.getUsername();
                url = new URL("https://sportsreserves.000webhostapp.com/actualizarPerfil.php?"
                        + "imagen=" + imagenCodificada
                        + "&nombre=" + txtNombre2.getText()
                        + "&apellidos=" + txtApellidos2.getText()
                        + "&edad=" + txtEdad.getText()
                        + "&peso=" + txtPeso.getText()
                        + "&altura=" + txtAltura.getText()
                        + "&fecha_nacimiento=" + txtFecha.getText()
                        + "&genero=" + txtGenero.getText()
                        + "&username=" + username);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String linea = "";
                    while ((linea = br.readLine()) != null) {
                        dbMessage += linea;
                    }

                    br.close();
                    inputStream.close();
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getApplication().getApplicationContext(), dbMessage, Toast.LENGTH_SHORT).show();
        }
    }

    public class obtenerPerfil extends AsyncTask<Void, Void, PerfilUsuario> {
        PerfilUsuario user;
        String username;

        public obtenerPerfil(String username) {
            this.username = username;
        }

        @Override
        protected PerfilUsuario doInBackground(Void... voids) {
            String phpUrl = "https://sportsreserves.000webhostapp.com/obtenerPerfil.php?username=" + username;
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(phpUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(5000);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                Gson gson = new Gson();
                user = gson.fromJson(stringBuilder.toString(), PerfilUsuario.class);
                Log.v("user", user.toString());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect(); // Asegúrate de cerrar la conexión
                }
            }
            return user;
        }

        @Override
        protected void onPostExecute(PerfilUsuario perfil) {
            super.onPostExecute(perfil);
            perfilUsuario = perfil;
            if (perfilUsuario != null) {
                mostrarPerfil();
            }
        }
    }


    private String codificarImagen(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, byteArrayOutputStream);
        byte[] bytearray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytearray, Base64.DEFAULT);
    }

    private Bitmap decodificarImagen(String encodedImage) {
        byte[] byteArray = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }


    /*private void pedirpermisoParaFoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }
            else
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PEDI_PERMISO_ESCRITURA);
        }
        else{
            DialogoQueHacer();
        }
    }
    //Dialogo que nos muestra que podemos seleccionar la cámara o la galería
    private void DialogoQueHacer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Como quieres hacer la foto");
        builder.setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                hacerLaFotoConCalidad();
            }
        });
        builder.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SeleccionarGaleria();
            }
        });
        builder.show();
    }
    //Función que pide los permisos que necesitamos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PEDI_PERMISO_ESCRITURA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DialogoQueHacer();
            } else {
                Toast.makeText(this, "Sin permiso de escritura no hay foto de calidad", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //Función que hace la foto con Calidad
    private void hacerLaFotoConCalidad() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            fichero = crearFicheroFoto();
        } catch (IOException e) {
            e.printStackTrace();
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider",fichero));

        if (intent.resolveActivity(getPackageManager()) != null) { //Debo permitir la consulta en el android manifest
            startActivityForResult(intent, VENGO_DE_CAMARA_CON_CALIDAD);
        } else {
            Toast.makeText(Perfil.this, "Necesitas cámara", Toast.LENGTH_SHORT).show();
        }

    }
    //Función que nos abre la galería
    private void SeleccionarGaleria(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, VENGO_DE_GALERIA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VENGO_DE_GALERIA && resultCode == RESULT_OK) {
            imagenUri = data.getData();
            foto.setImageURI(imagenUri);

        } else if (requestCode == VENGO_DE_CAMARA_CON_CALIDAD) {
            if(resultCode==RESULT_OK){
                Bitmap rotacion=BitmapFactory.decodeFile(fichero.getAbsolutePath());
                ExifInterface exif = null;
                try {
                    exif = new ExifInterface(fichero.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                int grados=0;

                Matrix matrix= new Matrix();
                switch (orientation){
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        matrix.postRotate(90);
                        grados=90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        matrix.postRotate(180);
                        grados=180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        matrix.postRotate(270);
                        grados=270;
                        break;
                    default:
                        break;
                }
                foto.setImageBitmap(rotacion);
                foto.setRotation(grados);
            }
            else{
                fichero.delete();
            }

            //Guardamos la foto en nuestro sharedpreferences
            SharedPreferences misDatos = getSharedPreferences(NOMBRE_FICHERO, MODE_PRIVATE);
            SharedPreferences.Editor editor = misDatos.edit();
            editor.putString(ETIQUETA_FOTO, fichero.getAbsolutePath());
            editor.commit();
        }
    }
    //Función que crea el Fichero
    private File crearFicheroFoto() throws IOException {
        String fechaYHora = new SimpleDateFormat("yyyyMMdd_HH_mm_ss_").format(new Date());
        String nombreFichero = "fotos_" + fechaYHora;
        File carpetaFotos = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        carpetaFotos.mkdirs();
        File imagenAltaResolucion = File.createTempFile(nombreFichero, ".jpg", carpetaFotos);
        //String imagenlocalizacion =imagenAltaResolucion.getAbsolutePath();
        //guardarlo en las preferencias y luego traermelo en el onCreate
        return imagenAltaResolucion;
    }
*/

}