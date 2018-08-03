public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

   private final LayoutInflater mInflater;
   private List<Word> mWords; // Cached copy of words

   WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

   @Override
   public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
       return new WordViewHolder(itemView);
   }

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

   void setWords(List<Word> words){
       mWords = words;
       notifyDataSetChanged();
   }

   // getItemCount() is called many times, and when it is first called,
   // mWords has not been updated (means initially, it's null, and we can't return null).
   @Override
   public int getItemCount() {
       if (mWords != null)
           return mWords.size();
       else return 0;
   }

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
