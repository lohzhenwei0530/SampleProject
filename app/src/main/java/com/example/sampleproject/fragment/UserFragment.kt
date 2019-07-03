package com.example.sampleproject.fragment

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
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
import com.example.sampleproject.framework.presenter.UserPresenter
import com.example.sampleproject.framework.view.UserView
import com.example.sampleproject.model.MainViewModel
import com.example.sampleproject.network.model.User
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : BaseFragment(), UserView, UserAdapter.OnInteractListener {

    lateinit var presenter: UserPresenter

    lateinit var adapter: UserAdapter

    lateinit var mainViewModel: MainViewModel

    var listUser :ArrayList<User> = ArrayList<User>()

    var counter = 0

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

        et_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(p0.isNullOrEmpty()){
                    adapter.populateUserList(listUser)
                }
                searchText(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
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
        listUser = user as ArrayList<User>
        hideLoadingProgress()
        insertUserBySequence()
    }

    override fun onUserClick(user: User) {
        mainViewModel.user =user
        findNavController().navigate(R.id.action_to_google_map_fragment)
    }

    private fun insertUserBySequence(){
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                if (counter < listUser.size) {
                    handler.postDelayed(this, 2000)
                    adapter.insertUser(listUser.get(counter))
                    counter++
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }
        handler.post(runnable)
    }

    private fun setUpAdapter() {
        rv_user.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = UserAdapter(context!!)
        adapter.setOnInteractListener(this)
        rv_user.adapter = adapter
    }

    private fun searchText(text : String){
        val newList = listUser.filter {
            it.name.contains(text) || it.email.contains(text)
        }
        adapter.populateUserList(newList as ArrayList<User>)
    }
}