// Para implementar un adapter de un recyclerView se necesita extender de la clase 
// RecyclerView.Adapter se debe colocar el nombre del holder como tipo de dato <NombreAdapter.NombreClaseInterna > que necesita para que
// contenga la informacion de cada elemento de la lista , para esto se debe definir una clase interna  
// que se extienda de RecyclerView.ViewHolder, en esta clase definimos todos los elementos que tendra 
// nuestro  item , se debe crear un constructor para esta clase ,el constructo recibe un view y con el metodo findById 
// seobtiene cada vista y se la asocia  a la vista declarada en la calse.
// en el adaqpter se deben sobre escribir tres metodos onBindHOlder ,onCreateViewHOlder y getItemCount
//
//
//

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
