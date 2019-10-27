package com.perros_felices.scenes.boards


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.perros_felices.R
import com.perros_felices.infrastructure.binding_adapters.BoardRecyclerViewAdapter
import com.perros_felices.models.Pet
import com.perros_felices.scenes.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_main_board.*

class MainBoardFragment: Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //isLoggedUser()

        val listOfItems = arrayListOf<Pet>()
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))
        listOfItems.add(Pet("Inflacion", "Dr. Roberth"))


        val viewAdapter = BoardRecyclerViewAdapter(listOfItems, null, activity!!.baseContext)
        showOnScreen(viewAdapter, contentRecyclerView)
    }

    private fun isLoggedUser() {
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            if (authenticationState == LoginViewModel.AuthenticationState.UNAUTHENTICATED) {
                findNavController().navigate(R.id.login_fragment)
            } else if (authenticationState == LoginViewModel.AuthenticationState.AUTHENTICATED) {
                val toolbar = activity!!.findViewById<Toolbar>(R.id.toolbar)
                toolbar.visibility = View.VISIBLE
            }
        })
    }

    private fun showOnScreen(viewAdapter2: RecyclerView.Adapter<*>, recycleView: RecyclerView ){
        val linearLayoutManager = GridLayoutManager(activity!!.baseContext, 3)
        val viewManager: RecyclerView.LayoutManager = linearLayoutManager
        val viewAdapter: RecyclerView.Adapter<*> = viewAdapter2

        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}