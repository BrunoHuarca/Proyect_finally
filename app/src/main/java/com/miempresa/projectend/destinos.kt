package com.miempresa.projectend



import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_destinos.*


class destinos : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var map: GoogleMap

    private val Plaza = LatLng(-16.407738, -71.474439)
    private lateinit var mMap: GoogleMap
    var nombre = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)


    var destino = ""
    var latitud = ""
    var longitud = ""
    var info = ""
    var fotosr = R.drawable.segrampo


    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)
        createFragment()




        val recibidos = this.intent.extras
        if (recibidos != null) {
            destino = intent.extras!!.getString("destino")!!
            latitud = intent.extras!!.getString("latitud")!!
            longitud = intent.extras!!.getString("longitud")!!
            info = intent.extras!!.getString("info")!!
            fotosr = intent.extras!!.getInt("fotos")!!

        }

        lblDestino.setText(destino)
        lblCoordenadas.setText("$latitud , $longitud")
        lblInfo.setText(info)
        //ima.setImageResource(fotosr)

        btnVolver.setOnClickListener(){
            volverLista(v = null)
        }
    }

    fun volverLista(v: View?) {
        val intent = Intent(this, ViewUsers::class.java)
        startActivity(intent)
    }
    private fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        createPolylines()
        enableLocation()

        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }

        coordenada = LatLng(-16.407738, -71.474439)
        marcadorDestino = mMap.addMarker(
            MarkerOptions()
                .position(coordenada)
                .title("Enpresa de transporte: $nombre")
        )



        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15f))
        // Eventos
        mMap.setOnMarkerClickListener(this)
    }
    fun moverCamara(view: View?){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza, 18f))
    }
    fun agregarMarcador(view:View?){
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            )
                .title("Mi Ubicacion")
        )
    }


    private fun  createPolylines(){
        val polylineOptions = PolylineOptions()
            .add(LatLng(-16.407738, -71.474439))
            .add(LatLng(-16.408008, -71.477894))
            .add(LatLng(-16.407815, -71.478302))
            .add(LatLng(-16.408489, -71.485204))
            .add(LatLng(-16.411127, -71.484765))
            .add(LatLng(-16.411312, -71.486101))
            .add(LatLng(-16.411960, -71.485980))
            .add(LatLng(-16.412112, -71.486943))
            .add(LatLng(-16.412567, -71.486857))
            .add(LatLng(-16.412907, -71.489118))
            .add(LatLng(-16.412454, -71.489252))
            .add(LatLng(-16.412570, -71.490234))
            .add(LatLng(-16.410720, -71.490442))
            .add(LatLng(-16.411364, -71.494968))
            .add(LatLng(-16.415197, -71.494311))
            .add(LatLng(-16.415623, -71.497140))
            .add(LatLng(-16.414414, -71.498739))
            .add(LatLng(-16.412489, -71.501641))
            .add(LatLng(-16.410971, -71.503924))
            .add(LatLng(-16.408643, -71.507452))
            .add(LatLng(-16.406073, -71.511414))
            .add(LatLng(-16.404461, -71.513839))
            .add(LatLng(-16.405418, -71.515446))
            .add(LatLng(-16.403179, -71.516352))
            .add(LatLng(-16.402767, -71.516534))
            .add(LatLng(-16.401519, -71.518334))
            .add(LatLng(-16.399397, -71.521592))
            .add(LatLng(-16.402408, -71.525133))
            .add(LatLng(-16.405588, -71.529002))
            .add(LatLng(-16.407320, -71.531103))
            .add(LatLng(-16.405443, -71.532976))


            .add(LatLng(-16.405410, -71.532941))
            .add(LatLng(-16.403267, -71.530299))
            .add(LatLng(-16.400022, -71.526286))
            .add(LatLng(-16.398115, -71.523888))
            .add(LatLng(-16.399472, -71.521669))
            .add(LatLng(-16.401519, -71.518334))
            .add(LatLng(-16.402767, -71.516534))
            .add(LatLng(-16.403179, -71.516352))
            .add(LatLng(-16.405418, -71.515446))
            .add(LatLng(-16.404461, -71.513839))
            .add(LatLng(-16.406073, -71.511414))
            .add(LatLng(-16.408643, -71.507452))
            .add(LatLng(-16.415284, -71.497448))
            .add(LatLng(-16.415642, -71.497131))
            .add(LatLng(-16.415210, -71.494291))
            .add(LatLng(-16.413916, -71.494508))
            .add(LatLng(-16.413232, -71.490096))
            .add(LatLng(-16.412907, -71.489118))
            .add(LatLng(-16.412567, -71.486857))
            .add(LatLng(-16.412112, -71.486943))
            .add(LatLng(-16.411960, -71.485980))
            .add(LatLng(-16.411312, -71.486101))
            .add(LatLng(-16.411127, -71.484765))
            .add(LatLng(-16.408489, -71.485204))
            .add(LatLng(-16.407815, -71.478302))
            .add(LatLng(-16.408008, -71.477894))
            .add(LatLng(-16.407738, -71.474439))

            .color(ContextCompat.getColor(this,R.color.colorRed))

        val polyline = map.addPolyline(polylineOptions)
    }

    private fun createMarker() {
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            //si
            map.isMyLocationEnabled = true
        } else {
            //no
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this,"Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(this,"Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this,"Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}