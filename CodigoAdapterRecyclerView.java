// Para implementar un adapter de un recyclerView se necesita extender de la clase 
// RecyclerView.Adapter se debe colocar el nombre del holder como tipo de dato <NombreAdapter.NombreClaseInterna > que necesita para que
// contenga la informacion de cada elemento de la lista , para esto se debe definir una clase interna  
// que se extienda de RecyclerView.ViewHolder, en esta clase definimos todos los elementos que tendra 
// nuestro  item , se debe crear un constructor para esta clase ,el constructo recibe un view y con el metodo findById 
// seobtiene cada vista y se la asocia  a la vista declarada en la calse.
// en el adaqpter se deben sobre escribir tres metodos onBindHOlder ,onCreateViewHOlder y getItemCount
//
//
//en la ultima parte del codigo se tiene implementado el manejo de eventos 
// esto consiste de 4 pasos que estan enumerados como (1).(2),(3) y (4).

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

   private final LayoutInflater mInflater;
   private List<Word> mWords; // lista de objetos a mostrar en el recycler view  

   WordListAdapter(Context context) { 
      
      mInflater = LayoutInflater.from(context); }

   // creamos el holder donde se le pasa el tiem view 
   @Override
   public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
       return new WordViewHolder(itemView);
   }

   //asociamos los datos de la lista a un holder creado
   @Override
   public void onBindViewHolder(WordViewHolder holder, int position) {
       if (mWords != null) {
           Word current = mWords.get(position);
           holder.wordItemView.setText(current.getWord());
       } else {
           // Covers the case of data not being ready yet.
           holder.wordItemView.setText("No Word");
       }
   }

   // metodo para acutalizar la lista de datos
   void setWords(List<Word> words){
       mWords = words;//
       notifyDataSetChanged();//notifica de los cambios al adapter y redibuja la interfaz
   }

   // getItemCount() este metodo es llamado siempre, y cuando es llamado por primera vez,
   // se debe hacer una comprobacion de que este objeto es distinto de null
   //devuelve la cantidad de elementos de la lista 
   @Override
   public int getItemCount() {
       if (mWords != null)
           return mWords.size();
       else return 0;
   }

    // clase interna para el viewHolder
   class WordViewHolder extends RecyclerView.ViewHolder {
       private final TextView wordItemView;

       private WordViewHolder(View itemView) {
           super(itemView);
           wordItemView = itemView.findViewById(R.id.textView);
       }
   }
}



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
 
   private List<UserModel> userModelList;
 
   public UserAdapter(List<UserModel> userModelList) {
       this.userModelList = userModelList;
   }
 
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_row, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }
 
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       String name = userModelList.get(position).getName();
       holder.name.setText(name);
   }
 
   @Override
   public int getItemCount() {
       return userModelList.size();
   }
 
   public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView name;
       public ViewHolder(View v) {
           super(v);
           name = (TextView) v.findViewById(R.id.textUserName);
       }
   }
 
}



// manejo de eventos 

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    // ...

    //1//
    /*****Creating OnItemClickListener *****/

    // (2) declaramos un oyente 
    private OnItemClickListener listener;
    // 1   define la interfaz
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
   //(2)
    // Define the method that allows the parent activity or fragment to define the listener
   //defino el metodo que me permite que la actividad o fragmento envie la implementacion del listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public TextView tvHometown;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            this.tvHometown = (TextView) itemView.findViewById(R.id.tvHometown);
            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);//(4) desencadeno los eventos para el listener 
                        }
                    }
                }
            });
        }
    }

    // ...
}
//Entonces podemos adjuntar un controlador de clic al adaptador con:


//(3) implemento el oyente y lo adjunto a la clase hijo 
// en la actividad o fragmento 
ContactsAdapter adapter = ...;
adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {
        String name = users.get(position).name;
        Toast.makeText(UserListActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
    }
});
