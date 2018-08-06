
// lo primero que se debe hacer es crear una clase que extienda de DiffUtil.Callback

public class PersonasDiffCallback extend DiffUtil.Calback{
     
   List<Persona> oldPersonas=new ArrayList<Persona>;
   List<Persona> NewPersona=new ArrayList<Persona>;



    public PersonasDiffCallback (ArrayList <Persona> oldPersonas, ArrayList <Persona> newPersonas) {
    this.oldPersonas = oldPersonas;
    this.newPersonas = newPersonas;
      }


   @Override
    public int getOldListSize () {
      return oldPersonas.size ();
    }
    
    @Override
    public int getNewListSize () {
    return newPlaces.size ();
    }
    
    
    @Override
    public boolean areItemsTheSame (int oldItemPosition, int newItemPosition) {
    return oldPersonas.get (oldItemPosition) .getId () == newPersonas.get (newItemPosition) .getId ();
    }


    @Override
    public boolean areContentsTheSame (int oldItemPosition, int newItemPosition) {
    Persona oldPersona = oldPersonas.get (oldItemPosition );
    Persona newPersona = newPersonas.get (newItemPosition );
    return oldPersona.getNombre () == newPersona.getNombre () &&
            oldPersona.getApellido (). equals (newPersona.getApellido ());
     }
     
     
    @Override
   Object Public getChangePayload (int oldItemPosition, int newItemPosition) {
   Persona oldPersona = oldPlaces.get (oldItemPosition );
   Persona newPersona = newPlaces.get (newItemPosition );
    Bundle bundle= neew  Bundle ();
    if (oldPersona.getNombre ()! = newPersona.getNombre ()) {
        bundle.putString ("Nombre", newPersona.getNombre ());
    }
    if(!oldPersona.getApellido().equals(newPersona.getApellido ())) {
        bundle.putString ("Apellido", newPersona.getApellido ());
    }
    return Bundle;
}
 }



// despues de escribir el metodo se debe escribir metodo en el adaptador
// de manera tal que se pueda activar el uso de callback
void actualizar(List<Persona> personas){

      PersonaDiffCallback callback = new PersonaDiffCallback (this.personas,personas); 
      DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff (callback); this.Personas.clear (); 
      this.Personas.addAll (Personas); 
      diffResult.dispatchUpdatesTo (this);
}


 // en el la clase main despues que se obtiene la nueva lista se debe llamar a este metodo
 
 adapter.actualizar(NuevaLista);
 
 //por ejemplo si se esta usando live data y viewModel
 
 ViewModel.getAllPersonas.observe(this,new Observer<List<Persona>>() {
            @Override
            public void onChanged(@Nullable final List<Persona> personas) {
                adapter.actualizar(personas);
            }
 )
