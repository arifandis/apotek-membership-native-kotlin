package com.cahstudio.tesapotekk24.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahstudio.tesapotekk24.R
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse
import com.cahstudio.tesapotekk24.ui.addmember.AddMemberActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewModel by viewModel<MainViewModel>()

    private lateinit var rvMember: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var fabAdd: FloatingActionButton

    private lateinit var memberAdapter: MemberAdapter

    private var memberList = mutableListOf<MemberListResponse.Member>()

    private var partnerID: String? = null
    private var partnerCode: String? = null

    private val REQUEST_ADD_MEMBER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        partnerID = intent.getStringExtra("partnerID")
        partnerCode = intent.getStringExtra("partnerCode")

        memberAdapter = MemberAdapter(this, memberList)

        initView()
        getMemberList()
    }

    fun initView() {
        rvMember = findViewById(R.id.rvMember)
        progressBar = findViewById(R.id.progressBar)
        fabAdd = findViewById(R.id.fabAdd)

        rvMember.layoutManager = LinearLayoutManager(this)
        rvMember.adapter = memberAdapter

        fabAdd.setOnClickListener(this)
    }

    fun getMemberList() {
        rvMember.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        partnerID?.let { partnerCode?.let { it1 -> mViewModel.getMemberList(it, it1).observe(this, {
            rvMember.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            if (it.status == "success") {
                memberList.clear()
                memberList.addAll(it.members)
                memberAdapter.notifyDataSetChanged()
            }
        })}}
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fabAdd -> {
                startActivityForResult(Intent(this, AddMemberActivity::class.java)
                    .putExtra("partnerID", partnerID)
                    .putExtra("partnerCode", partnerCode)
                    , REQUEST_ADD_MEMBER)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_ADD_MEMBER) {
            if (resultCode == RESULT_OK) {
                getMemberList()
            }
        }
    }
}