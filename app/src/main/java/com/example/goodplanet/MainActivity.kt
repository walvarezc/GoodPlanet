package com.example.goodplanet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goodplanet.databinding.ActivityMainBinding
import com.example.goodplanet.modelo.Plastico
import com.google.android.gms.common.util.CollectionUtils.listOf



class MainActivity : AppCompatActivity(),RecyclerAdapter.OnPlasticoClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.myToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val listPlastico: List<Plastico> = listOf(
            Plastico(
                "PET \n" +
                        "Plástico transparente.",
                "https://th.bing.com/th/id/OIP.wfvQ3SIC0GW1l3hJf2GBVwHaIC?w=152&h=180&c=7&r=0&o=5&pid=1.7"
            ),
            Plastico(
                "PP \n" +
                        "Utensilios de cocina, platos de plástico para microondas, pajitas de bebida,cubiertos desechables, etc.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEfm2lmLv6QX4wq5zeVcKp4uB_MUcZgsQg-g&usqp=CAU"
            ),
            Plastico(
                "PS \n" +
                        "(Poliestireno). Material electrónico y espuma de embalaje, material negro",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQf0p7wQY6x2EALzk2gdqL96NI8sz2UwUT6JA&usqp=CAU"
            ),
            Plastico(
                "PVC \n" +
                        "Es rígido y duro, se utiliza para embalar elementos alimenticios y no comestibles.",
                "https://www.mahecsa.com.pe/img/munecotubo.png"
            ),
            Plastico(
                "PDPE \n" +
                        "Envases de lácteos, zumos, champú, perfume, detergentes,etc.",
                "https://industriadelplastico.files.wordpress.com/2020/12/papeleras-chile.jpg"
            ),
            Plastico(
                "LDPE \n" +
                        "Bolsas de alimentos y de basura, botellas exprimibles o tapas flexibles.",
                "https://previews.123rf.com/images/monticello/monticello1601/monticello160100077/51291509-reciclaje-de-la-muestra-con-botellas-de-pl%C3%A1stico-y-las-tapas-.jpg"
            ),
            Plastico(
                "METAL \n" +
                        "Metales.",
                "https://media.istockphoto.com/vectors/metal-waste-many-isolated-on-white-background-pile-of-metal-garbage-vector-id1219369200"
            ),
            Plastico(
                "CARTON \n" +
                        "Cartones.",
                "https://previews.123rf.com/images/clairev/clairev1004/clairev100400040/6860783-cartoon-cardboard-box.jpg"
            ),
            Plastico(
                "PAPEL \n" +
                        "Papeles.",
                "https://rinconeducativo.org/contenidoextra/guia/papel.png"
            ),
            Plastico(
                "VIDRIO \n" +
                        "Viddrio.",
                "https://cdn5.dibujos.net/dibujos/pintados/201947/reciclaje-de-vidrio-naturaleza-medioambiente-11664655.jpg"
            ),
            Plastico(
                "COBRE \n" +
                        "Cobre.",
                "https://thumbs.dreamstime.com/b/alambre-de-cobre-del-pedazo-35072513.jpg"
            ),
            Plastico(
                "LATAS \n" +
                        "Latas.",
                "https://previews.123rf.com/images/izakowski/izakowski1309/izakowski130900034/22015607-cartoon-illustration-of-old-empty-can-junk-clip-art.jpg"
            ),
            Plastico(
                "ALUMINIO \n" +
                        "Aluminio.",
                "https://thumbs.dreamstime.com/b/reciclaje-de-aluminio-74226601.jpg"
            ),


            )
        binding.recyclerView.adapter = RecyclerAdapter(this, listPlastico, this)

    }

    override fun onImagenClick(imagen: String) {
        val intent = Intent(this, ImageDetailActivity::class.java)
        intent.putExtra("imageUrl", imagen)
        startActivity(intent)

    }

    override fun onItemClick(nombre: String) {

        Toast.makeText(this, "Descripción de categotia: $nombre", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.salir -> {
            finish()
            true
        }
        R.id.action_search -> {
            Toast.makeText(this, R.string.txt_action_search, Toast.LENGTH_LONG).show()
            true
        }

        R.id.action_phone -> {
            Toast.makeText(this, R.string.txt_action_phone, Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    fun onPhone(item: android.view.MenuItem) {
        val intento = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "3168105790"))
        startActivity(intento)
    }


}