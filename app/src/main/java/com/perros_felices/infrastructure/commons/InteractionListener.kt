package com.perros_felices.infrastructure.commons

interface InteractionListener<M> {
    fun onListenerFragmentInteraction(item: M?)
}