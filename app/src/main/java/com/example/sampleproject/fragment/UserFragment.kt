package com.example.sampleproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.R
import com.example.sampleproject.adapter.UserAdapter
import com.example.sampleproject.framework.base.BaseFragment
import com.example.sampleproject.framework.presenter.LoginPresenter
import com.example.sampleproject.framework.presenter.UserPresenter
import com.example.sampleproject.framework.view.LoginView
import com.example.sampleproject.framework.view.UserView
import com.example.sampleproject.model.MainViewModel
import com.example.sampleproject.network.model.User
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : BaseFragment(), UserView, UserAdapter.OnInteractListener {

    lateinit var presenter: UserPresenter

    lateinit var adapter: UserAdapter

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onStart() {
        super.onStart()
        presenter = UserPresenter(this)
        presenter.bindView(this)
        showLoadingProgress()
        presenter.getUser()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("User")
        setUpAdapter()
    }


    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onGetUserSuccess(user: List<User>) {
        adapter.populateUserList(user)
        hideLoadingProgress()
    }

    override fun onUserClick(user: User) {
        mainViewModel.user =user
        findNavController().navigate(R.id.action_to_google_map_fragment)
    }

    private fun setUpAdapter() {
        rv_user.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = UserAdapter(context!!)
        adapter.setOnInteractListener(this)
        rv_user.adapter = adapter
    }
}